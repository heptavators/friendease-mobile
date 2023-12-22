package com.heptavators.friendease.ui.screen.schedule

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.heptavators.friendease.ui.components.ScheduleList
import com.heptavators.friendease.ui.components.UiState
import com.heptavators.friendease.ui.screen.getViewModelFactory
import com.heptavators.friendease.ui.theme.FriendeaseTheme

@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
    navigateToScheduleDetail: (String) -> Unit = {},
    makePayment: (String) -> Unit = {},
    viewModel: ScheduleViewModel = viewModel(
        factory = getViewModelFactory(context = LocalContext.current)
    ),
) {

    val orderData by viewModel.orderData
    LaunchedEffect(key1 = true,){
        if (orderData is UiState.Loading){
            viewModel.getOrder()
            Log.d("ScheduleScreen", "ScheduleScreen: ${orderData}")
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
                    text = "Schedule",
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            when(orderData){
                is UiState.Loading -> {
                    Text(text = "Loading")
                }
                is UiState.Success -> {
                    val data = (orderData as UiState.Success).data
                    ScheduleList(
                        data = data.data,
                        navigateToScheduleDetail = navigateToScheduleDetail,
                        makePayment = makePayment
                    )
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
fun ScheduleScreenPreview() {
    FriendeaseTheme {
        ScheduleScreen(
            navigateToLogin = {},
        )
    }
}