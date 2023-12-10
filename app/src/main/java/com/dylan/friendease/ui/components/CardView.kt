package com.dylan.friendease.ui.components

import androidx.cardview.widget.CardView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.dylan.friendease.R
import com.dylan.friendease.ui.theme.FriendeaseTheme
import com.dylan.friendease.ui.theme.roboto


@Composable
fun CardView(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
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
                        Image(
                            painter = painterResource(id = R.drawable.tos),
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
                                text = "Fujikawa Chiai",
                                fontFamily = roboto,
                                fontSize = 10.sp,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                            )

                            Text(
                                text = "@fuji_chiai",
                                fontFamily = roboto,
                                fontSize = 8.sp,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                    Text(
                        text = "Saya adalah seorang musisi, bisa bermain gitar, dan pendengar yang ba...",
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
                        modifier = Modifier.padding(top = 25.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy((-80).dp)
                    ) {
                        AsyncImage(
                            model = "https://cdn.myanimelist.net/images/voiceactors/3/59891.jpg",
                            contentDescription = "Fujikawa Chiai",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .size(80.dp)
                                .height(100.dp)
                                .weight(9f/16f)
                                .aspectRatio(9f/16f)
                                .zIndex(1f)
                        )
                        AsyncImage(
                            model = "https://i.pinimg.com/236x/61/28/ce/6128ce1c1cb48cb285dbc75be30b5a1e.jpg",
                            contentDescription = "Fujikawa Chiai",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .size(80.dp)
                                .height(100.dp)
                                .weight(9f/16f)
                                .aspectRatio(9f/16f)
                                .graphicsLayer(alpha = 0.8f)
                                .zIndex(0.7f)
                        )
                        AsyncImage(
                            model = "https://todayidol.com/wp-content/uploads/2018/03/Fujikawa-Chiai_Maneki-Kecak.jpg",
                            contentDescription = "Fujikawa Chiai",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .size(80.dp)
                                .height(100.dp)
                                .weight(9f/16f)
                                .aspectRatio(9f/16f)
                                .graphicsLayer(alpha = 0.8f)
                                .zIndex(0.5f)
                        )
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
        CardView()
    }
}