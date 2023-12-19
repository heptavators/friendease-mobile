package com.heptavators.friendease.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.heptavators.friendease.R
import com.heptavators.friendease.data.model.UserData
import com.heptavators.friendease.ui.components.UiState
import com.heptavators.friendease.ui.screen.getViewModelFactory
import com.heptavators.friendease.ui.theme.FriendeaseTheme
import com.heptavators.friendease.ui.theme.roboto

@Composable
fun ProfileScreen(
    navigateToLogin: () -> Unit,
    navigateToNotification: () -> Unit,
    navigateToWelcome: () -> Unit,
    viewModel: ProfileViewModel = viewModel(
        factory = getViewModelFactory(context = LocalContext.current)
    ),
    ) {

    val profileData by viewModel.profileData
    LaunchedEffect(profileData){
        if(profileData is UiState.Loading) viewModel.user()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(0.dp, 0.dp, 25.dp, 25.dp)
                )
                .padding(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                when(profileData){
                    is UiState.Loading -> {
                        Text(
                            text = "Loading...",
                            fontFamily = roboto,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    is UiState.Success -> {
                        val profile = (profileData as UiState.Success<UserData>).data
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = profile.avatar,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(72.dp)
                                    .clip(CircleShape)
                                    .border(
                                        width = 2.dp,
                                        color = Color.White,
                                        shape = CircleShape
                                    )
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Column(
                                modifier = Modifier
                                    .align(Alignment.Top)
                            ) {
                                Text(
                                    text = profile.fullname,
                                    fontFamily = FontFamily.Default,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                )

                                Text(
                                    text = "@${profile.username}",
                                    fontFamily = FontFamily.Default,
                                    color = Color.White,
                                    fontWeight = FontWeight.Normal,
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = profile.email,
                                    fontFamily = FontFamily.Default,
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }
                    }
                    is UiState.Error -> {
                        Text(
                            text = (profileData as UiState.Error).errorMessage,
                            fontFamily = roboto,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    is UiState.NotLogged -> {
//                        navigateToWelcome()
                    }
                    else -> {}
                }


                Spacer(modifier = Modifier.height(12.dp))
                WalletCard()
            }
        }

        Text(
            text = "Akun",
            fontFamily = roboto,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_user_full),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Pengaturan Profil",
                fontFamily = roboto,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .align(Alignment.CenterHorizontally),
            color = Color.Gray,
            thickness = 1.dp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_shield_check),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Pengaturan Keamanan",
                fontFamily = roboto,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .align(Alignment.CenterHorizontally),
            color = Color.Gray,
            thickness = 1.dp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bell_full),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier.clickable { navigateToNotification() },
                text = "Notifikasi",
                fontFamily = roboto,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .align(Alignment.CenterHorizontally),
            color = Color.Gray,
            thickness = 1.dp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_logout),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier.clickable {
                    viewModel.logout()
                    navigateToWelcome()
                },
                text = "Logout",
                fontFamily = roboto,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .align(Alignment.CenterHorizontally),
            color = Color.Gray,
            thickness = 1.dp
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = "Info Lainya",
            fontFamily = roboto,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_shield_keyhole),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Kebijkan Privasi",
                fontFamily = roboto,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .align(Alignment.CenterHorizontally),
            color = Color.Gray,
            thickness = 1.dp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_shield_slash),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Syarat & Ketentuan",
                fontFamily = roboto,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .align(Alignment.CenterHorizontally),
            color = Color.Gray,
            thickness = 1.dp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_interrogation),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Pusat Bantuan",
                fontFamily = roboto,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .align(Alignment.CenterHorizontally),
            color = Color.Gray,
            thickness = 1.dp
        )
    }
}



@Composable
fun WalletCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(112.dp)
            .padding(16.dp)
            .shadow(2.dp)
            .background(Color.Transparent),
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_inbox_in),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 16.dp)
                )

                Column {
                    Text(
                        text = "Uang Masuk",
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Rp1.200.000",
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))

                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .height(100.dp)
                        .width(1.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_inbox_out),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 16.dp)
                )

                Column {
                    Text(
                        text = "Uang Keluar",
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Rp100.000",
                    )
                    Spacer(modifier = Modifier.width(50.dp))
                }
            }

        }
    }
}


@Composable
@Preview(showBackground = true)
fun MenuItemPreview() {
    FriendeaseTheme {
        ProfileScreen(
            navigateToLogin = {},
            navigateToNotification = {},
            navigateToWelcome = {},
            )
    }
}
