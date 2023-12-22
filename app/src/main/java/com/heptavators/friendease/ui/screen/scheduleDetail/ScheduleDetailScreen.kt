package com.heptavators.friendease.ui.screen.scheduleDetail

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.heptavators.friendease.R
import com.heptavators.friendease.data.model.DataOrder
import com.heptavators.friendease.data.model.DetailTalentData
import com.heptavators.friendease.ui.components.UiState
import com.heptavators.friendease.ui.screen.detailTalent.DetailTalentScreen
import com.heptavators.friendease.ui.screen.detailTalent.DetailViewModel
import com.heptavators.friendease.ui.screen.getViewModelFactory
import com.heptavators.friendease.ui.screen.scheduleDetail.ScheduleDetailViewModel
import com.heptavators.friendease.ui.theme.FriendeaseTheme
import com.heptavators.friendease.ui.theme.roboto

@Composable
fun ScheduleDetailScreen(
    id: String,
    makePayment: (String) -> Unit = {},
    navigateToBack: () -> Unit = {},
    viewModel: ScheduleDetailViewModel = viewModel(
        factory = getViewModelFactory(context = LocalContext.current)
    )
) {
    var deskripsiTalentTeks by remember {
        mutableStateOf("")
    }
    var selectedRating by remember { mutableStateOf(0) }
    var showSheet by remember { mutableStateOf(false) }

    val orderData = viewModel.orderData.value

    var imagePath1 by remember { mutableStateOf<String?>(null) }
    var imagePath2 by remember { mutableStateOf<String?>(null) }
    var imagePath3 by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(orderData) {
        if (orderData is UiState.Loading) viewModel.getOrderById(id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .background(color = Color.White)
            .padding(top = 16.dp)
    ) {
        when (orderData) {
            is UiState.Loading -> {
                Text(text = "Loading")
            }

            is UiState.Success -> {
                val orderData = orderData.data
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(25.dp))
                        .background(color = MaterialTheme.colorScheme.background)
                        .padding(16.dp, 0.dp, 16.dp, 16.dp)
                ) {
                    Image(
//                        model = orderData.image,
                        painter = painterResource(id = R.drawable.slider),
                        contentDescription = "Fujikawa Chiai",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box {
                            Text(
                                text = orderData.name,
                                fontFamily = roboto,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .padding(top = 8.dp)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp)
                                )
                        ) {
                            Text(
                                text = orderData.transactionStatus,
                                fontFamily = roboto,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                modifier = Modifier
                                    .padding(8.dp, 6.dp, 8.dp, 6.dp)

                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Spacer(modifier = Modifier.height(15.dp))
                    Column {
                        Text(
                            text = "Harga: ${orderData.price}",
                            fontFamily = roboto,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                    Column {
                        Text(
                            text = "Tanggal: ${orderData.date}",
                            fontFamily = roboto,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                    Column {
                        Text(
                            text = "Tipe: ${orderData.type}",
                            fontFamily = roboto,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }

                    Column {
                        Text(
                            text = "Jam: ${orderData.startHour} - ${orderData.endHour}",
                            fontFamily = roboto,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }

                    Column {
                        Text(
                            text = "Biaya Total: ${orderData.totalAmount}",
                            fontFamily = roboto,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
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
                }
                Button(
                    onClick = {
                        makePayment(orderData.token)
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

            is UiState.Error -> {
                Text(text = "Error")
            }

            is UiState.NotLogged -> {
                Text(text = "Not Logged")
            }
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
                    AsyncImage(
                        model = imagePath,
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
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var inputValue by remember { mutableStateOf(initialText) }

//    OutlinedTextField(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(200.dp)
//            .then(modifier),
//        value = inputValue,
//        onValueChange = {
//            inputValue = it
//            onValueChange(it)
//        },
//        label = {
//            Text(text = name)
//        },
//        placeholder = { Text(text = "Type here") },
//        shape = RoundedCornerShape(percent = 7),
//        keyboardOptions = KeyboardOptions.Default,
//    )
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .then(modifier)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(7.dp)
            )
            .verticalScroll(rememberScrollState()),
        value = inputValue,
        onValueChange = {
            inputValue = it
            onValueChange(it)
        },
        textStyle = TextStyle.Default.copy(
            fontSize = 16.sp,
            color = Color.Black
        ),
        decorationBox = { innerTextField ->
            Column(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(7.dp)
                    )
                    .padding(4.dp)
            ) {
                innerTextField()
            }
        },
    )
}

@Composable
@Preview(showBackground = true)
fun newDetailPreview() {
    FriendeaseTheme {
//        ScheduleDetailScreen(onBackPressed = {})
    }
}