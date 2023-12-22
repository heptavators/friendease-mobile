package com.heptavators.friendease.ui.screen.register

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.heptavators.friendease.R
import com.heptavators.friendease.ui.components.UiState
import com.heptavators.friendease.ui.screen.getViewModelFactory
import com.heptavators.friendease.ui.screen.register2.DeskripsiScreen
import com.heptavators.friendease.ui.screen.register2.HobiScreen
import com.heptavators.friendease.ui.screen.register2.JenisKelamin
import com.heptavators.friendease.ui.screen.register2.NamaLengkapScreen
import com.heptavators.friendease.ui.screen.register2.TanggalLahir
import com.heptavators.friendease.ui.screen.register2.TempatTinggal
import com.heptavators.friendease.ui.screen.register2.WelcomeRegister
import com.heptavators.friendease.ui.theme.FriendeaseTheme
import com.heptavators.friendease.ui.theme.roboto

@Composable
fun Register2Screen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    viewModel: RegisterViewModel = viewModel(
        factory = getViewModelFactory(context = LocalContext.current)
    )
) {
    var tabIndex by remember { mutableStateOf(0) }

    val progress by remember { mutableStateOf(0.1f) }

    var fullname by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var bod by remember { mutableStateOf("") }
    var locationId by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var userPreferences by remember { mutableStateOf("") }
    var tags by remember { mutableStateOf<List<String>>(emptyList()) }

    val tagsData by viewModel.tagsData
    val locationData by viewModel.locationData
    val registerStatus by viewModel.registerStatus

    LaunchedEffect(tagsData, locationData){
        if (tagsData is UiState.Loading) viewModel.getTags()
        if (locationData is UiState.Loading) viewModel.getLocation()
    }




    val nextPage = { index: Int ->
        tabIndex = index
    }

    val onSubmit = { fullname: String, username: String, bod: String, locationId: String, gender: String, userPreferences: String, tags: List<String> ->
        viewModel.changeProfile(fullname, username, "${bod}T16:00:00.000Z", "d62abf30-cb6f-4690-aee9-a7dd9be70aeb", gender, userPreferences, listOf("8aff57d8-ec74-4e5f-b741-570115c05a6d", "76cc7aae-ebb2-4577-862d-75e34f8f3f24"))
        if (registerStatus is UiState.Success) navigateToHome()
        Log.d("asdasd", listOf(fullname, username, "${bod}T16:00:00.000Z", "d62abf30-cb6f-4690-aee9-a7dd9be70aeb", gender, userPreferences, listOf("8aff57d8-ec74-4e5f-b741-570115c05a6d", "76cc7aae-ebb2-4577-862d-75e34f8f3f24")).toString())
    }

    when (tabIndex) {
        0 -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
            ) {
                WelcomeRegister()
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(
                        onClick = {
                            nextPage(1)
                        },
                        modifier = Modifier
                            .padding(top = 0.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Saya setuju",
                            fontSize = 23.sp,
                            color = Color.White,
                        )
                    }
                }
            }
        }
        1 -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
            ) {
                NamaLengkapScreen(
                    fullname = fullname,
                    onValueChangeFullname = { fullname = it },
                    username = username,
                    onValueChangeUsername = { username = it },
                )
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(
                        onClick = {
                            nextPage(2)
                        },
                        enabled = !(fullname.isEmpty() || username.isEmpty()),
                        modifier = Modifier
                            .padding(top = 0.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "berikutnya",
                            fontSize = 23.sp,
                            color = Color.White,
                        )
                    }
                }
            }
        }
        2 -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
            ) {
                TanggalLahir(
                    bod = bod,
                    onValueChangeBod = { bod = it },
                )
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(
                        onClick = {
                            nextPage(3)
                        },
                        enabled = !(bod.isEmpty()),
                        modifier = Modifier
                            .padding(top = 0.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "berikutnya",
                            fontSize = 23.sp,
                            color = Color.White,
                        )
                    }
                }
            }
        }
        3 -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
            ) {
                TempatTinggal(
                    locationId = locationId,
                    onValueChangeLocationId = { locationId = it },
                    locationData = locationData,
                )
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(
                        onClick = {
                            nextPage(4)
                        },
                        enabled = !(locationId.isEmpty()),
                        modifier = Modifier
                            .padding(top = 0.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "berikutnya",
                            fontSize = 23.sp,
                            color = Color.White,
                        )
                    }
                }
            }
        }
        4 -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
            ) {
                JenisKelamin(
                    gender = gender,
                    onValueChangeGender = { gender = it },
                )
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(
                        onClick = {
                            nextPage(5)
                        },
                        enabled = !(gender.isEmpty()),
                        modifier = Modifier
                            .padding(top = 0.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "berikutnya",
                            fontSize = 23.sp,
                            color = Color.White,
                        )
                    }
                }
            }
        }
        5 -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
            ) {
                HobiScreen(
                    tags = tags,
                    onValueChangeTags = { tags = it },
                )
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(
                        onClick = {
                            nextPage(6)
                        },
                        enabled = !(tags.isEmpty()),
                        modifier = Modifier
                            .padding(top = 0.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "berikutnya",
                            fontSize = 23.sp,
                            color = Color.White,
                        )
                    }
                }
            }
        }
        6 -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
            ) {
                DeskripsiScreen(
                    userPreferences = userPreferences,
                    onValueChangeUserPreferences = { userPreferences = it },
                )
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(
                        onClick = {
                            onSubmit(fullname, username, bod, locationId, gender, userPreferences, tags)
                        },
                        enabled = !(userPreferences.isEmpty()),
                        modifier = Modifier
                            .padding(top = 0.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Cari Temanmu",
                            fontSize = 23.sp,
                            color = Color.White,
                        )
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun RegisterScreenPreview() {
//    FriendeaseTheme {
////        Register2Screen(
////            navigateToLogin = {},
////            navigateToHome = {}
////        )
//    }
//}