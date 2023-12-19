package com.heptavators.friendease

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.Constants
import com.google.firebase.messaging.FirebaseMessaging
import com.heptavators.friendease.ui.theme.FriendeaseTheme
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback
import com.midtrans.sdk.corekit.core.MidtransSDK
import com.midtrans.sdk.corekit.core.TransactionRequest
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme
import com.midtrans.sdk.corekit.models.CustomerDetails
import com.midtrans.sdk.corekit.models.ItemDetails
import com.midtrans.sdk.corekit.models.snap.TransactionResult
import com.midtrans.sdk.uikit.SdkUIFlowBuilder

class MainActivity : ComponentActivity(), TransactionFinishedCallback {
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Notifications permission granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifications permission rejected", Toast.LENGTH_SHORT).show()
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = ContextCompat.getColor(this, R.color.primary2)

        setContent {
            FriendeaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(R.color.primary2)
                ) {
                    App(
                        makePayment = {
                            makePayment()
                        }
                    )
                }
            }
        }


        if (Build.VERSION.SDK_INT >= 33) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }

        FirebaseApp.initializeApp(this);
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(Constants.MessageNotificationKeys.TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            Log.d(Constants.MessageNotificationKeys.TAG, token)
            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
        })
    }

    private fun makePayment() {
        val clientKey = "SB-Mid-client-LqJkNbV20jHyMqtm"
        val merchantBaseUrl = "https://payment-backend-eo3rmejziq-uc.a.run.app/api/transaction/"

        SdkUIFlowBuilder.init()
            .setClientKey(clientKey)
            .setContext(this)
            .setMerchantBaseUrl(merchantBaseUrl)
//            .setMerchantBaseUrl(BuildConfig.BASE_URL)
//            .setClientKey(BuildConfig.CLIENT_KEY)
            .setTransactionFinishedCallback(this)
            .enableLog(true)
            .setColorTheme(CustomColorTheme("#777777", "#f77474", "#3f0d0d"))
            .buildSDK()
    }

    private fun clickPay() {
        MidtransSDK.getInstance().transactionRequest = transactionRequest("101", 50000000, 1, "hp gaming")
        MidtransSDK.getInstance().startPaymentUiFlow(this)

        // Toast.makeText(this, "Coba Click", Toast.LENGTH_LONG).show()
    }

    private fun customerDetails(): CustomerDetails {
        val cd = CustomerDetails()
        cd.customerIdentifier = "orang-123"
        return cd
    }

    private fun transactionRequest(id: String, price: Int, qty: Int, name: String): TransactionRequest {
        val request = TransactionRequest("Heptavators-${System.currentTimeMillis().toShort()} ", 10000.0)
        request.customerDetails = customerDetails()
        val details = ItemDetails(id, price.toDouble(), qty, name)
        val itemDetails = ArrayList<ItemDetails>()
        itemDetails.add(details)
        request.itemDetails = itemDetails
        return request
    }

    override fun onTransactionFinished(result: TransactionResult) {
        val snapToken = result.getResponse()
        Log.d("MyApp", "Snap Token: $snapToken")


        if (result.response != null) {
            when (result.status) {
                TransactionResult.STATUS_SUCCESS -> Toast.makeText(this, "Transaction Sukses ${result.response.transactionId}", Toast.LENGTH_LONG).show()
                TransactionResult.STATUS_PENDING -> Toast.makeText(this, "Transaction Pending ${result.response.transactionId}", Toast.LENGTH_LONG).show()
                TransactionResult.STATUS_FAILED -> Toast.makeText(this, "Transaction Failed ${result.response.transactionId}", Toast.LENGTH_LONG).show()
            }
            result.response.statusMessage
        } else if (result.isTransactionCanceled) {
            Toast.makeText(this, "Transaction Failed", Toast.LENGTH_LONG).show()
        } else {
            if (result.status.equals(TransactionResult.STATUS_INVALID, ignoreCase = true)) {
                Toast.makeText(this, "Transaction Invalid ${result.response.transactionId}", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Something Wrong", Toast.LENGTH_LONG).show()
            }
        }
    }

}
