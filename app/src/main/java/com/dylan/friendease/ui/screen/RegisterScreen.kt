package com.dylan.friendease.ui.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dylan.friendease.R
import com.dylan.friendease.ui.theme.FriendeaseTheme
import com.dylan.friendease.ui.theme.roboto

@Composable
fun RegisterScreen() {
    Column(
        modifier = Modifier
    ) {
        Spacer(modifier = Modifier.height(22.dp))
        Image(
            painter = painterResource(id = R.drawable.sign_up),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .width(200.dp)
                .height(300.dp)
                .background(Color.Transparent),
            alignment = Alignment.Center
        )
        Text(
            text = "Sign up",
            fontFamily = roboto,
            style = TextStyle(
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 0.5.sp,
                lineHeight = 30.sp,
                fontSize = 36.sp
            ),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Email",
            fontFamily = roboto,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp,
                lineHeight = 24.sp,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.Start)
        )
        BasicTextField(
            value = "",
            onValueChange = { },
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp)
                .background(
                    color = Color.Transparent,
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Password",
            fontFamily = roboto,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp,
                lineHeight = 24.sp,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.Start)
        )
        var password by remember { mutableStateOf(TextFieldValue()) }
        var passwordVisibility by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp)
                .background(
                    color = Color.Transparent,
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BasicTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )

            Image(
                painter = painterResource(id = if (passwordVisibility) R.drawable.ic_eye else R.drawable.ic_eye_close),
                contentDescription = "Toggle Password Visibility",
                modifier = Modifier
                    .clickable {
                        passwordVisibility = !passwordVisibility
                    }
                    .padding(horizontal = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Button(
            onClick = {
            },
            modifier = Modifier
                .padding(4.dp)
                .width(200.dp)
                .align(Alignment.End)
        ) {
            Text(
                text = "Sign up",
                fontFamily = roboto,
                fontSize = 23.sp,
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun MenuItemPreview() {
    FriendeaseTheme {
        RegisterScreen()
    }
}