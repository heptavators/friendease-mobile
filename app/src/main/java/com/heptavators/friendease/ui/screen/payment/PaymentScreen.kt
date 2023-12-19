package com.heptavators.friendease.ui.screen.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.heptavators.friendease.R
import com.heptavators.friendease.ui.components.CardHistoryPayment
import com.heptavators.friendease.ui.theme.FriendeaseTheme
import com.heptavators.friendease.ui.theme.roboto

@Composable
fun PaymentScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
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
                Text(
                    text = "Dapatkan promo menarik dari EaseWallet",
                    fontFamily = roboto,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Aktivasinya GRATIS dan dapatkan diskon untuk\npesanan pertamamu! S&K Berlaku~",
                    fontFamily = roboto,
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.background,
                            contentColor = MaterialTheme.colorScheme.primary
                        ),
                        contentPadding = PaddingValues(),
                        modifier = Modifier
                            .padding(10.dp, 10.dp, 10.dp, 10.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .defaultMinSize(minWidth = 20.dp, minHeight = 20.dp)
                    ) {
                        Text(
                            text = "Coba EaseWallet",
                            fontFamily = roboto,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(id = R.drawable.gifts),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White, shape = RoundedCornerShape(20.dp))
                .shadow(1.dp, shape = RoundedCornerShape(5.dp))
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "EaseWallet",
                        fontFamily = roboto,
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Rp. 120.000",
                        fontFamily = roboto,
                        color = Color(0xFF00937C),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(16.dp),
                    ) {
                    Text(
                        text = "Isi Saldo",
                        fontFamily = roboto,
                        color = Color(0xFF00937C),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .background(color = Color(0xFF00C3A4).copy(alpha = 0.3f), shape = RoundedCornerShape(5.dp))
                            .clip(shape = RoundedCornerShape(10.dp))
                            .padding(3.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_qr_scan),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "User",
                    modifier = Modifier
                        .weight(0.33f)
                        .size(30.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_inbox_in),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "User",
                    modifier = Modifier
                        .weight(0.33f)
                        .size(30.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_inbox_out),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "User",
                    modifier = Modifier
                        .weight(0.33f)
                        .size(30.dp)
                )
            }
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(shape = RoundedCornerShape(20.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.White, shape = RoundedCornerShape(20.dp))
                    .shadow(2.dp, shape = RoundedCornerShape(5.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White)
                    ) {
                        items(10) { index ->
                            CardHistoryPayment()
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                color = Color.Gray,
                                thickness = 1.dp
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun MenuItemPreview(){
    FriendeaseTheme {
        PaymentScreen()
    }
}
