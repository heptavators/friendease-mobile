package com.dylan.friendease.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dylan.friendease.R
import com.dylan.friendease.ui.components.CardView

@Composable
fun HomeScreen(
    navigateToLogin: () -> Unit,
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    color = Color(0xFF9B274F),
                    shape = RoundedCornerShape(0.dp, 0.dp, 25.dp, 25.dp)
                )
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFF9B274F)) // Warna di dalam Box
            ) {
                Text(
                    text = "Bosen sendirian? \nCari teman jalan!",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "#bersamalebihasyik",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(26.dp))
                Text(
                    text = "Ajak jalan sekarang",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(Color.Transparent),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tos),
                    contentDescription = null,
                    modifier = Modifier
                        .size(220.dp)
                        .background(Color.Transparent)
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            this.items(10) { index ->
                CardView(modifier = Modifier.fillMaxWidth().padding(8.dp))
            }
        }
    }
}



//    val dashboardData by viewModel.dashboardData
//
//    LaunchedEffect(key1 = true) {
//        if (dashboardData is UiState.Loading) {
//            viewModel.getDashboardData()
//        }
//        if (articleData is UiState.Loading) {
//            viewModel.getArticleData()
//        }
//    }

//    when (dashboardData) {
//        is UiState.Loading -> {
//            HomeDataShimmering()
//        }
//        is UiState.Success -> {
//            DashboardInfo((dashboardData as UiState.Success).data.data)
//        }
//        is UiState.Error -> {
//            Text(text = (dashboardData as UiState.Error).errorMessage)
//        }
//        is UiState.NotLogged -> {
//            navigateToLogin()
//        }
//    }


@Composable
@Preview(showBackground = true)
fun MenuItemPreview(){
    MaterialTheme{
        HomeScreen(
            navigateToLogin = {},
        )
    }
}