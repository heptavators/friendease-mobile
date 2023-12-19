package com.heptavators.friendease.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.heptavators.friendease.ui.theme.FriendeaseTheme

@Composable
fun CustomInput(
    name: String = "",
    initialText: String = "",
    onValueChange: (String) -> Unit
) {
    var inputValue by remember { mutableStateOf(initialText) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = inputValue,
        onValueChange = {
            inputValue = it
            onValueChange(it)
        },
        label = {
            Text(text = name)
        },
        placeholder = { Text(text = "Type here") },
        shape = RoundedCornerShape(percent = 40),
        keyboardOptions = KeyboardOptions.Default,
    )
}

@Composable
@Preview
fun CustomInputPreview(){
    FriendeaseTheme{
        CustomInput(
            name = "Name",
            initialText = "",
            onValueChange = {}
        )
    }
}