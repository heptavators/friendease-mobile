package com.heptavators.friendease.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.heptavators.friendease.data.model.TalentData
import com.heptavators.friendease.ui.theme.FriendeaseTheme
import com.heptavators.friendease.ui.theme.roboto
import com.heptavators.friendease.ui.utlis.truncateText


@Composable
fun CardView(
    data: TalentData,
    modifier: Modifier = Modifier,
    navigateToDetail: () -> Unit
) {
    val tagExample = listOf("gamer", "traveling", "actor", "musician", "singer")
    Box(
        modifier = Modifier
            .clickable {
                navigateToDetail()
            }
            .padding(5.dp)
            .fillMaxWidth()
            .shadow(2.dp, shape = RoundedCornerShape(10.dp))
    )
    {
        Column(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Left side content
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = data.auth.avatar,
                            contentDescription = null,
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Column(
                            modifier = Modifier
                                .align(Alignment.Top)
                        ) {
                            Text(
                                text = data.auth.fullname.truncateText(40),
                                fontFamily = roboto,
                                fontSize = 10.sp,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                            )

                            Text(
                                text = data.auth.username,
                                fontFamily = roboto,
                                fontSize = 8.sp,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }

//                    ganti yang bener nanti
                    Text(
                        text = data.auth.fullname.truncateText(60),
                        fontFamily = roboto,
                        fontSize = 7.sp,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                    )

                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Tag(text = "Musik", color = Color(0xFFE5A8BF))
                        Spacer(modifier = Modifier.width(8.dp))
                        Tag(text = "Curhat", color = Color(0xFFE5A8BF))
                        Spacer(modifier = Modifier.width(8.dp))
                        Tag(text = "Aktor", color = Color(0xFFE5A8BF))
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Tag(text = "Penyanyi", color = Color(0xFFE5A8BF))
                        Spacer(modifier = Modifier.width(8.dp))
                        Tag(text = "Penuh semangat", color = Color(0xFFE5A8BF))
                    }
                }
                // Right side image
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Row(
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy((-80).dp),
                    ) {
                        data.highlights.take(3).mapIndexed { index, highlight ->
                            val alphaValue = 1.3f - index * 0.3f
                            val zIndex = 1.3f - index * 0.3f
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .size(80.dp, 100.dp)
                                    .height(100.dp)
                                    .aspectRatio(9f / 16f)
                                    .weight(9f/16f)
                                    .zIndex(zIndex)
                            ) {
                                AsyncImage(
                                    model = highlight.highlightURL,
                                    contentDescription = highlight.highlightURL,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .graphicsLayer(alpha = alphaValue)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Tag(text: String, color: Color) {
    Box(
        modifier = Modifier
            .background(
                color = color,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(1.dp)
    ) {
        Text(
            text = text,
            fontFamily = roboto,
            color = Color.Black,
            fontSize = 7.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MenuItemPreview(){
    FriendeaseTheme{
//        CardView(talentData = null)
    }
}