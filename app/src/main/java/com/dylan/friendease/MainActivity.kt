package com.dylan.friendease

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.dylan.friendease.ui.screen.register2.NamaLengkapScreen
import com.dylan.friendease.ui.theme.FriendeaseTheme
import com.heptavators.friendease.R

class MainActivity : ComponentActivity() {
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
                    NamaLengkapScreen()
                }
            }
        }
    }
}
