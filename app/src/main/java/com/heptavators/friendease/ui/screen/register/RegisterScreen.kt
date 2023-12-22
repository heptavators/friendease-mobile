package com.heptavators.friendease.ui.screen.register


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.heptavators.friendease.R
import com.heptavators.friendease.ui.components.CustomInput
import com.heptavators.friendease.ui.components.PasswordInput
import com.heptavators.friendease.ui.components.UiState
import com.heptavators.friendease.ui.screen.getViewModelFactory
import com.heptavators.friendease.ui.theme.FriendeaseTheme
import com.heptavators.friendease.ui.theme.roboto

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit,
    viewModel: RegisterViewModel = viewModel(
        factory = getViewModelFactory(context = LocalContext.current)
    )
) {
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }

    val registerStatus by viewModel.registerStatus

    val onRegister = { email: String, password: String ->
        viewModel.register(email, password)
        Log.d("registerStatus", registerStatus.toString())
        Log.d("RegisterScreen", "email: $email, password: $password")
    }

    LaunchedEffect(registerStatus) {
        when (registerStatus) {
            is UiState.Success -> {
                navigateToLogin()
            }

            else -> {}
        }
    }

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

        when (registerStatus) {
            is UiState.Error -> {
                Text(
                    text = (registerStatus as UiState.Error).errorMessage,
                    fontFamily = roboto,
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 0.5.sp,
                        lineHeight = 30.sp,
                        fontSize = 16.sp
                    ),
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            else -> {}
        }

        Spacer(modifier = Modifier.height(5.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)

        ){
            Column(
                verticalArrangement = Arrangement.Center,
            ) {

                Text(
                    text = "Sudah punya akun?",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .clickable {
                            navigateToLogin()
                        }
                        .padding(4.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Button(
                    onClick = {
                        onRegister(emailText, passwordText)
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
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
    }
}



@Composable
@Preview(showBackground = true)
fun MenuItemPreview() {
    FriendeaseTheme {
//        RegisterScreen(
//            navigateToHome = { }
//        )
    }
}