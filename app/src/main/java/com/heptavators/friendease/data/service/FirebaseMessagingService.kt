package com.heptavators.friendease.data.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.Constants
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.heptavators.friendease.R
import com.squareup.picasso.Picasso

class FirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d(Constants.MessageNotificationKeys.TAG, "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
    }

    @SuppressLint("LongLogTag")
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(Constants.MessageNotificationKeys.TAG, "Dikirim dari: ${remoteMessage.from}")

        if (remoteMessage.notification != null) {
            if (remoteMessage.data.isNotEmpty()) {
                Log.d(Constants.MessageNotificationKeys.TAG, "Message data payload: ${remoteMessage.data}")
            }

        }

        Log.d(Constants.MessageNotificationKeys.TAG, "From: ${remoteMessage.from}")

        remoteMessage.notification?.let {
            showNotification(it.title.toString(), it.body.toString(), it.imageUrl.toString() )
            Log.d(Constants.MessageNotificationKeys.TAG, "Message Notification Title: ${it.title}")
            Log.d(Constants.MessageNotificationKeys.TAG, "Message Notification Body: ${it.body}")
            Log.d(Constants.MessageNotificationKeys.TAG, "Message Notification Image: ${it.imageUrl.toString()}")
        }
    }

    private fun showNotification(title: String, message: String, imageUrl: String) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.logo_friendease)
            .setColor(Color.parseColor("#9B274F"))
            .setLargeIcon(Picasso.get().load(imageUrl).get())
            .setStyle(
                NotificationCompat.BigPictureStyle()
                .bigPicture(Picasso.get().load(imageUrl).get()).bigLargeIcon(Picasso.get().load(imageUrl).get()))
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setSubText("test")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            builder.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }
        val notification = builder.build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    companion object {
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "channel_01"
        private const val CHANNEL_NAME = "Hepta channel"
    }

}