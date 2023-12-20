package com.dylan.friendease.ui.screen.scheduleDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dylan.friendease.R
import com.dylan.friendease.ui.screen.detailTalent.DetailScreen
import com.dylan.friendease.ui.theme.FriendeaseTheme
import com.dylan.friendease.ui.theme.roboto
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun ScheduleDetailScreen(onBackPressed: () -> Unit) {
    var deskripsiTalentTeks by remember {
        mutableStateOf("")
    }
    var selectedRating by remember { mutableStateOf(0) }
    var showSheet by remember { mutableStateOf(false) }

    var imagePath1 by remember { mutableStateOf<String?>(null) }
    var imagePath2 by remember { mutableStateOf<String?>(null) }
    var imagePath3 by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.slider),
            contentDescription = "Fujikawa Chiai",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Fujikawa Chiai",
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
        Text(
            text = "Deskripsi",
            fontFamily = roboto,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(top = 15.dp)
        )
        CustomInputDeskripsiTalent(
            name = "Bagikan penilaianmu tentang talent ini",
            initialText = deskripsiTalentTeks,
            onValueChange = { deskripsiTalentTeks = it }
        )
        Text(
            text = "Rating Talent",
            fontFamily = roboto,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(top = 8.dp)
        )

        RatingBar(
            rating = selectedRating.toFloat(),
            onRatingChanged = { newRating ->
                selectedRating = newRating.toInt()
            },
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "Unggah Gambar",
            fontFamily = roboto,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(top = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BoxWithAddIcon(imagePath1, onImageSelected = { imagePath1 = it })
            BoxWithAddIcon(imagePath2, onImageSelected = { imagePath2 = it })
            BoxWithAddIcon(imagePath3, onImageSelected = { imagePath3 = it })
        }
        Button(
            onClick = {
                showSheet = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp)
                )
                .fillMaxWidth()
                .align(Alignment.End)
        ) {
            Text(
                modifier = Modifier,
                text = "Bayar Sekarang",
                fontFamily = roboto,
                fontSize = 23.sp,
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
    }
}

@Composable
fun BoxWithAddIcon(
    imagePath: String?,
    onImageSelected: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(66.dp, 72.dp)
                    .background(color = Color.White)
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.primary,
                        shape = MaterialTheme.shapes.medium
                    )
                    .clip(MaterialTheme.shapes.medium)
                    .clickable {

                    },
                contentAlignment = Alignment.Center
            ) {
                if (imagePath != null) {
                    CoilImage(
                        data = imagePath,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RatingBar(
    rating: Float,
    onRatingChanged: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    var currentRating by remember { mutableStateOf(rating) }
    var isTouched by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
    ) {
        repeat(5) { index ->
            val isFilled = index < currentRating
            val isHalfFilled = !isFilled && index < currentRating + 0.5f

            val starIcon =
                if (isFilled) Icons.Default.Star
                else if (isHalfFilled) painterResource(id = R.drawable.ic_star_half)
                else painterResource(id = R.drawable.ic_star_outline)

            Icon(
                painter = starIcon as Painter,
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        onRatingChanged(index + 1f)
                    }
                    .size(24.dp),
                tint = if (isTouched) Color.Yellow else Color.Gray
            )
        }
    }
}

@Composable
fun CustomInputDeskripsiTalent(
    name: String = "",
    initialText: String = "",
    onValueChange: (String) -> Unit
) {
    var inputValue by remember { mutableStateOf(initialText) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        value = inputValue,
        onValueChange = {
            inputValue = it
            onValueChange(it)
        },
        label = {
            Text(text = name)
        },
        placeholder = { Text(text = "Type here") },
        shape = RoundedCornerShape(percent = 7),
        keyboardOptions = KeyboardOptions.Default,
    )
}

@Composable
@Preview(showBackground = true)
fun newDetailPreview() {
    FriendeaseTheme {
        DetailScreen(onBackPressed = {})
    }
}