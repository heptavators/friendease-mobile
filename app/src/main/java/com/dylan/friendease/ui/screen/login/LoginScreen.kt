package com.dylan.friendease.ui.screen.login

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dylan.friendease.R
import com.dylan.friendease.ui.components.CustomInput
import com.dylan.friendease.ui.components.PasswordInput
import com.dylan.friendease.ui.components.UiState
import com.dylan.friendease.ui.screen.getViewModelFactory
import com.dylan.friendease.ui.theme.FriendeaseTheme
import com.dylan.friendease.ui.theme.roboto

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    viewModel: LoginViewModel = viewModel(
        factory = getViewModelFactory(context = LocalContext.current)
    )
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginStatus by viewModel.loginStatus
    val isLoading by viewModel.isLoading

    val onLogin = {email: String, password: String ->
        viewModel.login(email, password)
        Log.d("loginStatus", loginStatus.toString())
        Log.d("LoginScreen", "email: $email, password: $password")
    }

    LaunchedEffect(loginStatus){
        when (loginStatus) {
            is UiState.Success -> {
                navigateToHome()
            }
            else -> {}
        }
    }

    Column(
        modifier = Modifier
    ) {
        Spacer(modifier = Modifier.height(22.dp))
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .width(280.dp)
                .height(300.dp)
                .background(Color.Transparent),
            alignment = Alignment.Center
        )
        Text(
            text = "Login",
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
                initialText = email,
                onValueChange = { email = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordInput(
                initialText = password,
                onValueChange = { password = it }
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Button(
            onClick = {
                onLogin(email, password)
            },
            enabled = !isLoading,
            modifier = Modifier
                .padding(4.dp)
                .width(200.dp)
                .align(Alignment.End)
        ) {
            Text(
                text = if(isLoading) "Loading" else "Login",
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
        LoginScreen(
            navigateToHome = { }
        )
    }
}