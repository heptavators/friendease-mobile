package com.heptavators.friendease.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import com.heptavators.friendease.data.model.NotificationData
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NotificationList(
    data: List<NotificationData>,
    status: String,
) {
    Log.d("test", data.toString())
    LazyColumn(
    modifier = Modifier
    .fillMaxSize()
    .padding(bottom = 10.dp, top = 3.dp)
    ) {
        this.items(data, key = { it.notificationId }) { notif ->
            CardNotif(notif, status)
            Spacer(
                modifier = Modifier
                    .height(2.dp)
            )
        }
    }
}