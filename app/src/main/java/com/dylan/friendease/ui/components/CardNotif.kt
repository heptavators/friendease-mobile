package com.dylan.friendease.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.dylan.friendease.R
import com.dylan.friendease.ui.theme.FriendeaseTheme

@Composable
fun CardNotif() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary,
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.ic_inbox_in),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(68.dp)
//                        .clip(CircleShape)
//                )
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.background, shape = CircleShape)
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_inbox_in),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(5.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Top)
                            .weight(0.6f),
                    ) {
                        Text(
                            text = "Saldo Masuk",
                            color = MaterialTheme.colorScheme.tertiary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        Text(
                            text = "Topup sebesar Rp100.000 berhasil!",
                            color = MaterialTheme.colorScheme.tertiary,
                            fontSize = 12.sp,
                        )
                    }
                    Column(
                        modifier = Modifier
                            .align(Alignment.Top)
                            .weight(0.3f)
                    ) {
                        Text(
                            text = "02 Dec 2023 • 13:18",
                            color = MaterialTheme.colorScheme.tertiary,
                            fontSize = 12.sp,
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun MenuCardNotif(){
    FriendeaseTheme{
        CardNotif()
    }
}