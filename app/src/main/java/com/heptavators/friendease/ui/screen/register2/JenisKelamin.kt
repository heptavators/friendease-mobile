package com.heptavators.friendease.ui.screen.register2

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import com.heptavators.friendease.ui.theme.FriendeaseTheme
import com.heptavators.friendease.ui.theme.roboto

@Composable
fun JenisKelamin (
    gender: String = "",
    onValueChangeGender: (String) -> Unit,
    modifier: Modifier = Modifier
){
    val progress by remember { mutableStateOf(0.55f) }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
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
            ) {
                Text(
                    text = "Jenis kelamin kamu?",
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
                ExposedDropdownJenisKelaminMenu(
                    gender = gender,
                    onValueChangeGender = onValueChangeGender,
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(top = 50.dp),
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
                            painter = painterResource(id = R.drawable.tanggal_lahir),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(312.dp),
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun ExposedDropdownJenisKelaminMenu(
    gender: String,
    onValueChangeGender: (String) -> Unit,
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val jeniskelamin = listOf("male", "female")

    var selectedItem by remember {
        mutableStateOf(gender)
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
                onValueChangeGender(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                }
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            label = {
                Text(text = "Jenis Kelamin", color = Color.Gray)
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
            jeniskelamin.forEach { label ->
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
                        onValueChangeGender(selectedItem)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Preview
@Composable
fun JenisKelaminScreenPreview() {
    FriendeaseTheme {
        JenisKelamin(
            gender = "",
            onValueChangeGender = {}
        )
    }
}