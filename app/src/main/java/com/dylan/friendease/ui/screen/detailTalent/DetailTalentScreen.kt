package com.dylan.friendease.ui.screen.detailTalent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.dylan.friendease.R
import com.dylan.friendease.data.model.DetailTalentData
import com.dylan.friendease.data.model.TalentData
import com.dylan.friendease.data.model.UserData
import com.dylan.friendease.ui.components.UiState
import com.dylan.friendease.ui.screen.getViewModelFactory
import com.dylan.friendease.ui.theme.FriendeaseTheme
import com.dylan.friendease.ui.theme.roboto

@Composable
fun DetailTalentScreen(
    id: String,
    navigateToBack: () -> Unit = {},
    viewModel: DetailViewModel = viewModel(
        factory = getViewModelFactory(context = LocalContext.current)
    ),
) {
    val talentData by viewModel.talentData
    LaunchedEffect(talentData) {
        if (talentData is UiState.Loading) viewModel.getTalentById(id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = {
                    navigateToBack()
                },
                modifier = Modifier.size(58.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.Center

        ) {
            when(talentData) {
                is UiState.Loading -> {
                    Text(text = "Loading")
                }
                is UiState.Success -> {
                    val talentData = (talentData as UiState.Success<DetailTalentData>).data
                    AsyncImage(
                        model = talentData.highlight.first().highlightURL,
                        contentDescription = "Fujikawa Chiai",
                        modifier = Modifier
//                            .size(200.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.Gray)
                    )
                }
                else -> {}
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        when(talentData){
            is UiState.Loading -> {
                Text(text = "Loading")
            }
            is UiState.Success -> {
                val talentData = (talentData as UiState.Success<DetailTalentData>).data
                Text(
                    text = talentData.auth.fullname,
                    fontFamily = roboto,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Pengajar, Suka Musik, Suka Membaca",
                    fontFamily = roboto,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 4.dp)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(4.dp)
                        .padding(vertical = 8.dp),
                    color = Color.Gray
                )
                Text(
                    text = talentData.auth.bio,
                    fontFamily = roboto,
                    fontSize = 20.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 8.dp)
                )
            }
            else -> {}
        }

        Spacer(modifier = Modifier.height(10.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .width(4.dp)
                .padding(vertical = 8.dp),
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Teks Komentar",
            fontFamily = roboto,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(top = 16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tos),
                    contentDescription = "User",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "Nama Pengguna",
                    fontFamily = roboto,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )

                    Text(
                        text = "4.5",
                        fontFamily = roboto,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Komentar pengguna akan ditampilkan di sini...",
            fontFamily = roboto,
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MenuItemPreview() {
    FriendeaseTheme {
        DetailTalentScreen(
            id = "1"
        )
    }
}