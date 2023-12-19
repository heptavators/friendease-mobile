package com.heptavators.friendease.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heptavators.friendease.R
import com.heptavators.friendease.ui.theme.FriendeaseTheme

@Composable
fun PasswordInput(
    initialText: String = "",
    onValueChange: (String) -> Unit
) {
    var password by remember { mutableStateOf(initialText) }
    var showPassword by remember { mutableStateOf(value = false) }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = password,
        onValueChange = {
            password = it
            onValueChange(it)
        },
        label = {
            Text(text = "Password")
        },
        placeholder = { Text(text = "Type password here") },
        shape = RoundedCornerShape(percent = 40),

        visualTransformation = if (showPassword) {

            VisualTransformation.None

        } else {

            PasswordVisualTransformation()

        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            if (showPassword) {
                IconButton(onClick = { showPassword = false }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_eye),
                        contentDescription = "hide_password",
                        modifier = Modifier.size(20.dp)
                    )
                }
            } else {
                IconButton(
                    onClick = { showPassword = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_eye_close),
                        contentDescription = "hide_password",
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        }
    )
}

@Composable
@Preview
fun PasswordInputPreview(){
    FriendeaseTheme{
        PasswordInput(
            initialText = "",
            onValueChange = {}
        )
    }
}