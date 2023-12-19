package com.dylan.friendease.ui.screen.register2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dylan.friendease.R
import com.dylan.friendease.ui.theme.FriendeaseTheme
import com.dylan.friendease.ui.theme.roboto

@Composable
fun DeskripsiScreen(
    modifier: Modifier = Modifier
){
    val progress by remember { mutableStateOf(1f) }
    var deskripsiTeks by remember {
        mutableStateOf("")
    }
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
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
                    .padding(top = 1.dp),
            ){
                Text(
                    text = "Satu langkah lagi nih.",
                    fontFamily = roboto,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = "Teman impianmu seperti apa sih?",
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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CustomInputDeskripsi(
                            name = "Saya ingin punya teman yang bisa diajak ke event cosplay bareng.",
                            initialText = deskripsiTeks,
                            onValueChange = { deskripsiTeks = it }
                        )
                        Spacer(modifier = Modifier.height(35.dp))
                        Image(
                            painter = painterResource(id = R.drawable.user_preferences),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(216.dp),
                        )
                        Spacer(modifier = Modifier.height(25.dp))
                        Button(
                            onClick = {
                            },
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Cari temanmu",
                                fontSize = 23.sp,
                                color = MaterialTheme.colorScheme.tertiary,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomInputDeskripsi(
    name: String = "",
    initialText: String = "",
    onValueChange: (String) -> Unit
) {
    var inputValue by remember { mutableStateOf(initialText) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp),
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

@Preview
@Composable
fun AddDeskripsiPreview() {
    FriendeaseTheme {
        DeskripsiScreen()
    }
}