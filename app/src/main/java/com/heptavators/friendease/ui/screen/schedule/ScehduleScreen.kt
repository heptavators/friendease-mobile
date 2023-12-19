package com.heptavators.friendease.ui.screen.schedule

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.heptavators.friendease.ui.components.CardSchedule
import com.heptavators.friendease.ui.theme.FriendeaseTheme

@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Terjadwal", "Selesai")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Box(
            modifier = Modifier
                .height(80.dp)
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Text(
                    text = "Schedule",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Color.Gray), shape = RectangleShape)
                .offset(y = 1.dp)
        )

        Column(modifier = Modifier
            .fillMaxWidth()
        ) {
            TabRow(selectedTabIndex = tabIndex, modifier = Modifier.border(0.dp, Color.Transparent)) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title, color = MaterialTheme.colorScheme.onPrimary) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .border(0.dp, Color.Transparent)
                    )
                }
            }
            when (tabIndex) {
                0 -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 10.dp, top = 3.dp)
                    ) {
                        this.items(30) { index ->
                            CardSchedule()
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp)
                                    .align(Alignment.CenterHorizontally),
                                color = Color.Gray,
                                thickness = 1.dp
                            )
                            Spacer(
                                modifier = Modifier
                                    .height(4.dp)
                            )
                        }
                    }
                }
                1 -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 10.dp, top = 3.dp)
                    ) {
                        this.items(3) { index ->
                            CardSchedule()
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp)
                                    .align(Alignment.CenterHorizontally),
                                color = Color.Gray,
                                thickness = 1.dp
                            )
                            Spacer(
                                modifier = Modifier
                                    .height(4.dp)
                            )
                        }
                    }
                }
            }
        }

    }
}




@Preview
@Composable
fun ScheduleScreenPreview() {
    FriendeaseTheme {
        ScheduleScreen(
            navigateToLogin = {},
        )
    }
}