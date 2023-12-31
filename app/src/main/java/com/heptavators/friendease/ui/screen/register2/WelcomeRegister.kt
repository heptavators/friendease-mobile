package com.heptavators.friendease.ui.screen.register2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.heptavators.friendease.R
import com.heptavators.friendease.ui.theme.roboto

@Composable
fun WelcomeRegister(
    modifier: Modifier = Modifier,
) {

    val progress by remember { mutableStateOf(0.1f) }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
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
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    Modifier.size(60.dp)
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
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontFamily = roboto,
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontSize = 15.sp,
                            )
                        ) {
                            append("Ciptakan suasana menyenangkan dengan menjaga informasi pribadi!.")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontFamily = roboto,
                                color = Color(0xFF2078D1),
                                fontSize = 15.sp,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append("Berteman Dengan Aman")
                        }
                    },
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
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(280.dp),
                        )
                    }
                }
            }
        }
    }

//    navigateToLogin()
}
