package com.heptavators.friendease.ui.screen.register


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.heptavators.friendease.R
import com.heptavators.friendease.ui.components.CustomInput
import com.heptavators.friendease.ui.components.PasswordInput
import com.heptavators.friendease.ui.theme.FriendeaseTheme
import com.heptavators.friendease.ui.theme.roboto

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
) {
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }

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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
        ){
            CustomInput(
                name = "Email",
                initialText = emailText,
                onValueChange = { emailText = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordInput(
                initialText = passwordText,
                onValueChange = { passwordText = it }
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Button(
            onClick = {
                navigateToHome()
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
        RegisterScreen(
            navigateToHome = { }
        )
    }
}