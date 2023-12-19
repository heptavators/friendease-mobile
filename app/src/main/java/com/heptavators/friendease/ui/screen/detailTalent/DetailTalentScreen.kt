package com.heptavators.friendease.ui.screen.detailTalent

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.heptavators.friendease.R
import com.heptavators.friendease.data.model.DetailTalentData
import com.heptavators.friendease.ui.components.UiState
import com.heptavators.friendease.ui.screen.getViewModelFactory
import com.heptavators.friendease.ui.theme.FriendeaseTheme
import com.heptavators.friendease.ui.theme.roboto


@Composable
fun DetailTalentScreen(
    id: String,
    navigateToBack: () -> Unit = {},
    viewModel: DetailViewModel = viewModel(
        factory = getViewModelFactory(context = LocalContext.current)
    ),
) {
    val scrollState = rememberScrollState()
    val talentData by viewModel.talentData


    LaunchedEffect(talentData) {
        if (talentData is UiState.Loading) viewModel.getTalentById(id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
            .background(color = Color.White)
            .padding(16.dp)
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
        when(talentData) {
            is UiState.Loading -> {
                Text(text = "Loading")
            }
            is UiState.Success -> {
                val talentData = (talentData as UiState.Success<DetailTalentData>).data
                AsyncImage(
                    model = talentData.auth.avatar,
                    contentDescription = talentData.auth.username,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
            }
            else -> {}
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.secondary,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .padding(4.dp)
                    ) {
                        Text(
                            text = "Wibu",
                            fontFamily = roboto,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.secondary,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .padding(4.dp)
                    ) {
                        Text(
                            text = "Suka Musik",
                            fontFamily = roboto,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.secondary,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .padding(4.dp)
                    ) {
                        Text(
                            text = "Cosplayer",
                            fontFamily = roboto,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star",
                            tint = Color.Yellow,
                            modifier = Modifier.size(16.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star",
                            tint = Color.Yellow,
                            modifier = Modifier.size(16.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star",
                            tint = Color.Yellow,
                            modifier = Modifier.size(16.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star",
                            tint = Color.Yellow,
                            modifier = Modifier.size(16.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star",
                            tint = Color.Yellow,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Column {
                        Divider(
                            modifier = Modifier
                                .height(5.dp)
                                .width(1.dp),
                            color = Color.Gray
                        )
                        Text(
                            text = "5",
                            fontFamily = roboto,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }
//            Divider(
//                modifier = Modifier
//                    .width(1.dp)
//                    .height(8.dp)
//            )
                    Spacer(modifier = Modifier.width(8.dp))

                    Row {
                        Text(
                            text = "220",
                            fontFamily = roboto,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Kegiatan",
                            fontFamily = roboto,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(4.dp)
                        .padding(vertical = 8.dp),
                    color = Color.Gray
                )

                Text(
                    text = "Deskripsi",
                    fontFamily = roboto,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(top = 8.dp)
                )
                Text(
                    text = "Saya adalah wibu sejati, watashi wibu desu yo, watashi anime daisuki desu, anime favorit watashi sousou no frieren desu, semoga kita bisa menjadi tomodachi desu.",
                    fontFamily = roboto,
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = TextStyle(
                        lineHeight = 17.5.sp
                    ),
                    modifier = Modifier
                        .padding(top = 5.dp)
                )
                Text(
                    text = "Penilaian Talent",
                    fontFamily = roboto,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(top = 8.dp)
                )
                Row (
                    modifier = Modifier
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ){
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = Color.Yellow,
                        modifier = Modifier.size(16.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = Color.Yellow,
                        modifier = Modifier.size(16.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = Color.Yellow,
                        modifier = Modifier.size(16.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = Color.Yellow,
                        modifier = Modifier.size(16.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = Color.Yellow,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${talentData.talent.rating}/5",
                        fontFamily = roboto,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "(96 Ulasan)",
                        fontFamily = roboto,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
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
                            text = "@farhan_kebab",
                            fontFamily = roboto,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Star",
                                tint = Color.Yellow,
                                modifier = Modifier.size(16.dp)
                            )
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Star",
                                tint = Color.Yellow,
                                modifier = Modifier.size(16.dp)
                            )
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Star",
                                tint = Color.Yellow,
                                modifier = Modifier.size(16.dp)
                            )
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Star",
                                tint = Color.Yellow,
                                modifier = Modifier.size(16.dp)
                            )
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Star",
                                tint = Color.Yellow,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Kak fuji asik",
                            fontFamily = roboto,
                            fontSize = 14.sp,
                            color = Color.Black
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.tos),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(80.dp, 100.dp)
                                    .clip(RoundedCornerShape(4.dp))
                            )
                            Spacer(modifier = Modifier.width(8.dp))

//                            Image(
//                                painter = painterResource(id = R.drawable.tos),
//                                contentDescription = null,
//                                modifier = Modifier
//                                    .size(80.dp, 100.dp)
//                                    .clip(RoundedCornerShape(4.dp))
//                            )
//                            Spacer(modifier = Modifier.width(8.dp))
//                            Image(
//                                painter = painterResource(id = R.drawable.tos),
//                                contentDescription = null,
//                                modifier = Modifier
//                                    .size(80.dp, 100.dp)
//                                    .clip(RoundedCornerShape(4.dp))
//                            )
                        }
                    }
                }
            }
            else -> {}
        }
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