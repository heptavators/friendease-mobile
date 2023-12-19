package com.dylan.friendease.ui.screen.notification

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dylan.friendease.data.model.NotificationResponse
import com.dylan.friendease.ui.components.CardNotif
import com.dylan.friendease.ui.components.NotificationList
import com.dylan.friendease.ui.components.UiState
import com.dylan.friendease.ui.screen.getViewModelFactory
import com.dylan.friendease.ui.theme.FriendeaseTheme

@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
    viewModel: NotificationViewModel = viewModel(
        factory = getViewModelFactory(context = LocalContext.current)
    ),
    ) {

    val notificationData by viewModel.notificationData
    LaunchedEffect(key1 = true) {
        if (notificationData is UiState.Loading){
            viewModel.getNotification()
            Log.d("notif", notificationData.toString())
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Box(
            modifier = Modifier
                .height(80.dp)
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Text(
                    text = "Notification",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Color.Gray), shape = RectangleShape)
                .offset(y = 1.dp)
        )

        Column(modifier = Modifier
            .fillMaxWidth()
        ) {
                when(notificationData){
                    is UiState.Loading -> {
                        Text(text = "Loading")
                    }
                    is UiState.Success -> {
                        val data = (notificationData as UiState.Success).data
                        if (data != null) {
                            NotificationList(
                                data = data.data,
                                status = "unread"
                            )
                        }
                    }
                    is UiState.Error -> {
                        Text(text = "Error")
                    }
                    is UiState.NotLogged -> {
                        navigateToLogin()
                    }
            }
            }
        }
}




@Preview
@Composable
fun WelcomeScreenPreview() {
    FriendeaseTheme {
        NotificationScreen(
            navigateToLogin = {},
        )
    }
}