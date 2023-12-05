package com.dylan.friendease.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier

@Composable

fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
) {
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

    navigateToLogin()
}