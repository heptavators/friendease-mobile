package com.heptavators.friendease.ui.screen.register2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.heptavators.friendease.R
import com.heptavators.friendease.ui.theme.FriendeaseTheme
import com.heptavators.friendease.ui.theme.roboto

@Composable
fun ScanWajahScreen(
    modifier: Modifier = Modifier
) {
    val progress by remember { mutableStateOf(0.75f) }
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize()
    ){
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
                    .fillMaxHeight()
                    .padding(top = 25.dp),
            ){
                Text(
                    text = "Upload selfie kamu",
                    fontFamily = roboto,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    modifier = Modifier.padding(top = 16.dp)
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
                            append("Untuk memastikan keamanan pengguna kami perlu identitas kamu. ")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontFamily = roboto,
                                color = Color(0xFF2078D1),
                                fontSize = 15.sp,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append("Bagaimana kami mengolah data")
                        }
                    },
                    modifier = Modifier.padding(top = 5.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.selfie),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(257.dp)
                        .padding(top = 16.dp)
                )
                Text(
                    text = "Scan wajah kamu",
                    fontFamily = roboto,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 26.sp,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)
                )

                Text(
                    text = "Kami akan scan wajahmu untuk cocokkan data di KTP. Ikuti instruksi berikut:",
                    fontFamily = roboto,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("1. Jangan pakai aksesoris yang menutupi wajahmu.\n")
                        }
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("2. Pastikan wajahmu terlihat dengan jelas\n")
                        }
                    },
                    fontFamily = roboto,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.hexagon_check),
                        contentDescription = null,
                        tint = Color(0xFF00C3A4),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "FriendEase menjamin keamanan datamu",
                        fontFamily = roboto,
                        color = Color.Gray,
                        fontSize = 16.sp,
                    )
                }
                Button(
                    onClick = {
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .height(48.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Scan Wajah",
                        fontSize = 23.sp,
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                }

//                Button(
//                    onClick = {},
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 16.dp)
//                        .height(48.dp)
//                        .background(
//                            color = MaterialTheme.colorScheme.primary,
//                            shape = RoundedCornerShape(8.dp)
//                        )
//                ) {
//                    Text(
//                        text = "Ambil Foto KTP",
//                        color = Color.White,
//                        fontSize = 16.sp,
//                        modifier = Modifier.padding(start = 8.dp)
//                    )
//                }
            }
        }
    }
}
@Preview
@Composable
fun ScanWajahPreview() {
    FriendeaseTheme {
        ScanWajahScreen()
    }
}

