package com.heptavators.friendease.ui.screen.payment

import android.os.Build
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun MidtransPayment(
    navigateToBack: () -> Unit = {},
    ) {
    val context = LocalContext.current

    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = {
                    navigateToBack()
                },
                modifier = Modifier.size(58.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                WebView(context)
            },
            update = { webView ->
                webView.apply {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                    }
                    webViewClient = WebViewClient()

                    webView.settings.javaScriptEnabled = true
                    webView.settings.javaScriptCanOpenWindowsAutomatically = true
                    webView.settings.domStorageEnabled = true
                    webView.settings.databaseEnabled = true
                    webView.settings.allowFileAccessFromFileURLs = true
                    webView.settings.allowFileAccess = true
                    webView.settings.allowContentAccess = true
                    webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE

                }
                webView.settings.javaScriptEnabled = true
                webView.settings.javaScriptCanOpenWindowsAutomatically = true
                webView.settings.domStorageEnabled = true
                webView.settings.databaseEnabled = true
                webView.settings.allowFileAccessFromFileURLs = true
                webView.settings.allowFileAccess = true
                webView.settings.allowContentAccess = true
                webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE

                // Load the URL

                webView.loadUrl("https://app.sandbox.midtrans.com/snap/v3/redirection/f7da5ed0-244e-44b9-8f41-e97dd770e6ca/")
            }
        )
    }
}
