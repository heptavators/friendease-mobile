package com.heptavators.friendease.ui.screen.detailTalent

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.heptavators.friendease.R
import com.heptavators.friendease.data.model.DetailTalentData
import com.heptavators.friendease.ui.components.CustomInput
import com.heptavators.friendease.ui.components.UiState
import com.heptavators.friendease.ui.screen.getViewModelFactory
import com.heptavators.friendease.ui.theme.FriendeaseTheme
import com.heptavators.friendease.ui.theme.roboto
import com.heptavators.friendease.ui.utlis.convertToAmPmFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Composable
fun DetailTalentScreen(
    id: String,
    navigateToBack: () -> Unit = {},
    makePayment: () -> Unit = {},
    viewModel: DetailViewModel = viewModel(
        factory = getViewModelFactory(context = LocalContext.current)
    ),
) {
    val scrollState = rememberScrollState()
    val talentData by viewModel.talentData

    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        BottomSheet(
            talentData = (talentData as UiState.Success<DetailTalentData>).data
        ) {
            showSheet = false
        }
    }

    LaunchedEffect(talentData) {
        if (talentData is UiState.Loading) viewModel.getTalentById(id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
            .background(color = Color.White)
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp, 16.dp, 16.dp),
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
        when (talentData) {
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
        when (talentData) {
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
                        .padding(16.dp, 8.dp, 16.dp, 0.dp)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 8.dp, 16.dp, 0.dp),
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
                        .padding(16.dp, 8.dp, 16.dp, 0.dp),
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

                    Spacer(modifier = Modifier.width(4.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Divider(
                            modifier = Modifier
                                .height(15.dp)
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
                        .padding(16.dp, 8.dp, 16.dp, 0.dp)
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
                        .padding(16.dp, 5.dp, 16.dp, 0.dp)
                )
                Text(
                    text = "Penilaian Talent",
                    fontFamily = roboto,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(16.dp, 9.dp, 16.dp, 0.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(16.dp, 8.dp, 16.dp, 0.dp),
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
                        .padding(16.dp, 10.dp, 16.dp, 0.dp),
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
                        }
                    }
                }
                Button(
                    onClick = {
//                                makePayment()
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
                        text = "Ajak Jalan",
                        fontFamily = roboto,
                        fontSize = 23.sp,
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                }
            }

            else -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    talentData: DetailTalentData,
    onDismiss: () -> Unit,
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    var activity by remember { mutableStateOf("") }
    var start_time by remember { mutableStateOf("") }
    var end_time by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    Log.d("konversi test", convertToAmPmFormat("16:09"))
    Log.d("BottomSheet", "time: $start_time, $end_time, date: $date")

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = MaterialTheme.colorScheme.background,
//        modifier = Modifier
//            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "KEGIATAN",
                    color = Color.Black,
                    fontFamily = roboto,
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 0.5.sp,
                        lineHeight = 30.sp,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                )
                TextField(
                    label = { Text("Activity") },
                    value = activity,
                    onValueChange = { activity = it },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
//            Text(
//                text = talentData.talent.talentId,
//                color = Color.Black,
//                modifier = Modifier
//                    .padding(8.dp)
//            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "JAM",
                    color = Color.Black,
                    fontFamily = roboto,
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 0.5.sp,
                        lineHeight = 30.sp,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    TimePicker(
                        label = "Jam Mulai",
                        value = start_time,
                        onValueChange = { start_time = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        keyboardActions = KeyboardActions.Default,
                        is24HourView = false,
                        modifier = Modifier
                            .weight(0.49f)
                    )
                    Text(
                        text = "-",
                        color = Color.Black,
                        modifier = Modifier
                            .weight(0.02f)
                    )
                    TimePicker(
                        label = "Jam Berakhir",
                        value = end_time,
                        onValueChange = { end_time = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        keyboardActions = KeyboardActions.Default,
                        is24HourView = false,
                        modifier = Modifier
                            .weight(0.49f)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "TANGGAL",
                    color = Color.Black,
                    fontFamily = roboto,
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 0.5.sp,
                        lineHeight = 30.sp,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                )
                DatePicker(
                    label = "Tanggal",
                    value = date,
                    onValueChange = { date = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    keyboardActions = KeyboardActions.Default,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
        Button(
            onClick = {
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .align(Alignment.End)
        ) {
            Text(
                text = "Ajak Sekarang",
                fontFamily = roboto,
                fontSize = 23.sp,
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
    }
}

@Composable
fun TimePicker(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    pattern: String = "HH:mm",
    is24HourView: Boolean = true,
    modifier: Modifier = Modifier,
) {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val time = if (value.isNotBlank()) LocalTime.parse(value, formatter) else LocalTime.now()
    val dialog = TimePickerDialog(
        LocalContext.current,
        R.style.Theme_Friendease_dialog,
        { _, hour, minute -> onValueChange(LocalTime.of(hour, minute).toString()) },
        time.hour,
        time.minute,
        is24HourView,
    )
    TextField(
        label = { Text(text = label) },
        value = value,
        onValueChange = { onValueChange(it) },
        enabled = false,
        modifier = Modifier
            .clickable { dialog.show() }
            .then(modifier),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
    )
}

@Composable
fun DatePicker(
    label: String,
    value: String,
    onValueChange: (String) -> Unit = {},
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    pattern: String = "yyyy-MM-dd",
    modifier: Modifier = Modifier,
) {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val date = if (value.isNotBlank()) LocalDate.parse(value, formatter) else LocalDate.now()
    val dialog = DatePickerDialog(
        LocalContext.current,
        R.style.Theme_Friendease_dialog,
        { _, year, month, dayOfMonth ->
            onValueChange(LocalDate.of(year, month + 1, dayOfMonth).toString())
        },
        date.year,
        date.monthValue - 1,
        date.dayOfMonth,
    )

    TextField(
        label = { Text(text = label) },
        onValueChange = { onValueChange(it) },
        value = value,
        enabled = false,
        modifier = Modifier
            .clickable { dialog.show() }
            .then(modifier),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
    )
}

@Composable
@Preview(showBackground = true)
fun MenuItemPreview() {
    FriendeaseTheme {
        DetailTalentScreen(
            id = "1",
        )
    }
}