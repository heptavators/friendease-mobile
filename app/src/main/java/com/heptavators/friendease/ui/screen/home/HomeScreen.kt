package com.heptavators.friendease.ui.screen.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.heptavators.friendease.R
import com.heptavators.friendease.ui.components.TalentList
import com.heptavators.friendease.ui.components.UiState
import com.heptavators.friendease.ui.screen.getViewModelFactory
import com.heptavators.friendease.ui.theme.FriendeaseTheme
import com.heptavators.friendease.ui.theme.roboto

@Composable
fun HomeScreen(
    navigateToLogin: () -> Unit,
    navigateToWelcome: () -> Unit,
    navigateToRegister2: () -> Unit,
    navigateToDetail: (String) -> Unit,
    viewModel: HomeViewModel = viewModel(
        factory = getViewModelFactory(context = LocalContext.current)
    ),
    ) {
    val talentData by viewModel.talentData
    val profile by viewModel.profileData

    LaunchedEffect(talentData) {
        if (talentData is UiState.Loading) viewModel.getAllTalent()
        if (talentData is UiState.Error)
        if (talentData is UiState.NotLogged) navigateToWelcome()
    }
    LaunchedEffect(profile) {
        if (profile is UiState.Loading) viewModel.user()
        if(profile is UiState.Success){
            val profile = (profile as UiState.Success).data
            if(profile.username == null){
                navigateToRegister2()
            }
            Log.d("profile", profile.toString())
        }
        if (profile is UiState.Error) navigateToWelcome()
        if (profile is UiState.NotLogged) navigateToLogin()
    }
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
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(0.dp, 0.dp, 25.dp, 25.dp)
                )
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = "Bosen sendirian? \nCari teman jalan!",
                    fontFamily = roboto,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "#bersamalebihasyik",
                    fontFamily = roboto,
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(26.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Ajak jalan sekarang",
                        fontFamily = roboto,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_right),
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .background(Color.White, shape = CircleShape)
                                .padding(1.dp)
                        )
                    }
                }
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
                    painter = painterResource(id = R.drawable.slider),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(1.dp)
                        .width(165.dp)
                        .height(114.dp)
                        .background(Color.Transparent)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(Color.White.copy(alpha = 0.5f), shape = CircleShape)
                            .padding(horizontal = 9.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
        }
        when(talentData){
            is UiState.Loading -> {
                Text(text = "Loading")
                Log.d("TalentList", "loading")
            }
            is UiState.Success -> {
                val data = (talentData as UiState.Success).data
                Log.d("TalentList", "TalentList: $data")
                TalentList(
                    data.data,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {
                Log.d("TalentList", (talentData as UiState.Error).errorMessage)
                Text(text = (talentData as UiState.Error).errorMessage)
            }
            is UiState.NotLogged -> {
                Log.d("TalentList", "notLogged")
                Text("Not Logged")
            }
            else -> {}
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MenuItemPreview() {
    FriendeaseTheme {
        HomeScreen(
            navigateToLogin = {},
            navigateToWelcome = {},
            navigateToDetail = {},
            navigateToRegister2 = {}
        )
    }
}
