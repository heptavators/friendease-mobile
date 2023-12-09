package com.dylan.friendease.ui.screen.notification

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dylan.friendease.R
import com.dylan.friendease.ui.components.CardNotif
import com.dylan.friendease.ui.components.CardView
import com.dylan.friendease.ui.screen.welcome.WelcomeScreen
import com.dylan.friendease.ui.theme.FriendeaseTheme
import kotlin.collections.none

@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
    ) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Belum Dibaca", "Sudah Dibaca")


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
                    text = "Notification",
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
                            CardNotif()
                            Spacer(
                                modifier = Modifier
                                    .height(2.dp)
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
                        this.items(10) { index ->
                            CardNotif()
                            Spacer(
                                modifier = Modifier
                                    .height(2.dp)
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
fun WelcomeScreenPreview() {
    FriendeaseTheme {
        NotificationScreen(
            navigateToLogin = {},
        )
    }
}