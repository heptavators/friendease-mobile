package com.heptavators.friendease.ui.screen.register2

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.heptavators.friendease.R
import com.heptavators.friendease.data.model.DataLocation
import com.heptavators.friendease.data.model.DataTags
import com.heptavators.friendease.data.model.LocationResponse
import com.heptavators.friendease.ui.components.UiState
import com.heptavators.friendease.ui.theme.FriendeaseTheme
import com.heptavators.friendease.ui.theme.roboto

@Composable
fun TempatTinggal(
    locationId: String = "",
    onValueChangeLocationId: (String) -> Unit,
    locationData: UiState<LocationResponse>,
    modifier: Modifier = Modifier
) {
    val progress by remember { mutableStateOf(0.4f) }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            LinearProgressIndicator(
                progress = progress,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 15.dp),
            ){
                Text(
                    text = "Tempat tinggal kamu?",
                    fontFamily = roboto,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp,
                        lineHeight = 37.5.sp
                    ),
                    fontSize = 32.sp,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "lokasi kamu sekarang",
                    fontFamily = roboto,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp,
                        lineHeight = 37.5.sp
                    ),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 16.dp)
                )
                ExposedDropdownMenu(
                    locationId = locationId,
                    onValueChangeLocationId = onValueChangeLocationId,
                    locationData = locationData,
                )
                Spacer(modifier = Modifier.height(25.dp))
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
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
                        Image(
                            painter = painterResource(id = R.drawable.tempat_tinggal),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(296.dp),
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .border(
                                    1.dp,
                                    MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .background(
                                    color = MaterialTheme.colorScheme.background,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(8.dp)
                        ) {
                                Text(
                                    text = "Bagikan lokasimu untuk tingkatkan kecocokan !",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun ExposedDropdownMenu(
    locationId: String,
    onValueChangeLocationId: (String) -> Unit,
    locationData: UiState<LocationResponse>,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val provinsi = listOf("Sumatra Selatan", "Kalimantan Timur", "Aceh", "Jawa Timur")

    var selectedItem by remember {
        mutableStateOf(locationId)
    }
    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column(
    ) {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = {
                selectedItem = it
                onValueChangeLocationId(it)
            },

            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                }
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            label = {
                Text(text = "Pilih Provinsi", color = Color.Gray)
            },
            trailingIcon = {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            },
            shape = RoundedCornerShape(percent = 40),
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
        ) {
            provinsi.forEach { label ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = label,
                            color = Color.Gray,
                            modifier = Modifier.padding(8.dp)
                        )
                    },
                    onClick = {
                        selectedItem = label
                        onValueChangeLocationId(selectedItem)
                        expanded = false
                    }
                )
            }
        }
    }
}



@Preview
@Composable
fun TempatTinggalScreenPreview() {
    FriendeaseTheme {
//        TempatTinggal(
//            locationId = "",
//            onValueChangeLocationId = {},
//        )
    }
}