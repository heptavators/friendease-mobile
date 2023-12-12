package com.dylan.friendease.ui.screen.welcome

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerDefaults.shape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dylan.friendease.AppViewModel
import com.dylan.friendease.R
import com.dylan.friendease.data.model.UserData
import com.dylan.friendease.ui.components.UiState
import com.dylan.friendease.ui.screen.getViewModelFactory
import com.dylan.friendease.ui.theme.FriendeaseTheme
import com.dylan.friendease.ui.theme.roboto
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: WelcomeViewModel = viewModel(
        factory = getViewModelFactory(context = LocalContext.current)
    ),
) {
    val validateToken by viewModel.isHaveToken

    LaunchedEffect(key1 = validateToken) {
        Log.d("zxczxczxc", validateToken.toString())
        viewModel.validateToken()
    }
    LaunchedEffect(validateToken){
        Log.d("asdasdasd", validateToken.toString())
        when(validateToken){
            is UiState.Success -> {
                val token = (validateToken as UiState.Success<Boolean>).data
                if (token) {
                    navigateToHome()
                } else {
                }
            }
            is UiState.NotLogged -> {}
            else -> {}

        }
    }
    Column(
        modifier = Modifier
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(22.dp))
        Image(
            painter = painterResource(id = R.drawable.welcome),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .width(200.dp)
                .height(300.dp)
                .background(Color.Transparent),
            alignment = Alignment.Center
        )
        Text(
            text = "Hello",
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
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Selamat datang di FriendEase, dimana kalian bisa mencari teman",
            fontFamily = roboto,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.5.sp,
                lineHeight = 30.sp,
                fontSize = 16.sp,
            ),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navigateToLogin()
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Login",
                fontFamily = roboto,
                fontSize = 23.sp,
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = {
                navigateToRegister()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = MaterialTheme.colorScheme.primary,
            ),
            modifier = Modifier
                .padding(4.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(30.dp))
                .fillMaxWidth()
                .clip(RoundedCornerShape(30.dp))

        ) {
            Text(
                text = "Sign up",
                fontFamily = roboto,
                fontSize = 23.sp,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    FriendeaseTheme {
        WelcomeScreen(
            navigateToLogin = {},
            navigateToHome = {},
            navigateToRegister = {}
        )
    }
}