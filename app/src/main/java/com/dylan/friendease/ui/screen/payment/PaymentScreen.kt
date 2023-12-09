package com.dylan.friendease.ui.screen.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dylan.friendease.R
import com.dylan.friendease.ui.components.CardHistoryPayment
import com.dylan.friendease.ui.screen.profile.WalletCard
import com.dylan.friendease.ui.theme.FriendeaseTheme
import com.dylan.friendease.ui.theme.roboto

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
                .height(200.dp)
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
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Aktivasinya GRATIS dan dapatkan diskon untuk\npesanan pertamamu! S&K Berlaku~",
                    fontFamily = roboto,
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .padding(2.dp)
                            .height(48.dp)
                            .width(140.dp)
                            .shadow(4.dp)
                            .background(Color.White, shape = RoundedCornerShape(16.dp)),
                        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary),
                    ) {
                        Text(
                            text = "Coba EaseWallet",
                            fontFamily = roboto,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
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
        CardHistoryPayment()
    }
}


@Composable
@Preview(showBackground = true)
fun MenuItemPreview(){
    FriendeaseTheme {
        PaymentScreen()
    }
}
