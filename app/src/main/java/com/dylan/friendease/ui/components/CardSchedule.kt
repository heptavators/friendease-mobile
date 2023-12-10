package com.dylan.friendease.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dylan.friendease.ui.theme.FriendeaseTheme

@Composable
fun CardSchedule() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.background,
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
                Box(
                    modifier = Modifier
                        .weight(0.7f)
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Column {
                            Text(
                                text = "10 Dec, 15:00",
                                color = Color.Gray,
                                fontSize = 10.sp,
                            )
                            AsyncImage(
                                model = "https://cdn.myanimelist.net/images/voiceactors/3/59891.jpg",
                                contentDescription = "Fujikawa Chiai",
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(MaterialTheme.colorScheme.background)
                            )
                        }
                        Column(
                            modifier = Modifier
                            .align(Alignment.Top)
                        )
                        {
                            Text(
                                text = "Fujikawa Chiai",
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = "@fuji_chiai",
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = "Memancing",
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .weight(0.3f)
                ){
                    Column(
                        modifier = Modifier
                    ){
                        Text(
                            text = "Rp. 100.000",
                            color = Color.Gray,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                        )
                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF00C3A4),
                                contentColor = Color.White
                            ),
                            contentPadding = PaddingValues(),
                            modifier = Modifier
                                .padding(2.dp, 4.dp, 2.dp, 4.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .defaultMinSize(minWidth = 15.dp, minHeight = 15.dp)
                        ) {
                            Text(
                                text = "Jalan Lagi",
                                fontSize = 10.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(7.dp, 3.dp, 8.dp, 3.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun CardSchedulePreview(){
    FriendeaseTheme{
        CardSchedule()
    }
}