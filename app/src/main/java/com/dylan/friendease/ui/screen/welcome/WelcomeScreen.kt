package com.dylan.friendease.ui.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerDefaults.shape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dylan.friendease.R
import com.dylan.friendease.ui.theme.FriendeaseTheme
import com.dylan.friendease.ui.theme.roboto
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit,
) {

    val progress by remember { mutableStateOf(0.1f) }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize()
    ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                LinearProgressIndicator(
                    progress = progress,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 50.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)

                    )
                    Text(
                        text = "Selamat datang di FriendEase",
                        fontFamily = roboto,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp,
                            lineHeight = 37.5.sp
                        ),
                        fontSize = 32.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = "Baca peraturan yuk",
                        fontFamily = roboto,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = "Jujur Yok",
                        fontFamily = roboto,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold
                        ),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    Text(
                        text = "Foto dan bio kamu harus benar-benar menggambarkan dirimu ya.",
                        fontFamily = roboto,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = TextStyle(
                            lineHeight = 17.5.sp
                        ),
                        fontSize = 15.sp,
                        modifier = Modifier.padding(top = 5.dp)
                    )
                    Text(
                        text = "Let's have fun",
                        fontFamily = roboto,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            lineHeight = 23.sp
                        ),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    Text(
                        text = "Ciptakan suasana menyenangkan dengan menjaga informasi pribadi! Berteman Dengan Aman.",
                        fontFamily = roboto,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = TextStyle(
                            lineHeight = 17.sp
                        ),
                        fontSize = 15.sp,
                        modifier = Modifier.padding(top = 5.dp)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.tos),
                                contentDescription = null,
                                modifier = Modifier.size(240.dp),
                            )
                            Button(
                                onClick = {
                                },
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Saya setuju",
                                    fontSize = 23.sp,
                                    color = MaterialTheme.colorScheme.tertiary,
                                )
                            }
                        }
                    }
                }
            }
        }

//    navigateToLogin()
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    FriendeaseTheme {
        WelcomeScreen(
            navigateToLogin = {},
            navigateToHome = {}
        )
    }
}