package com.heptavators.friendease.ui.screen.login

import android.os.Build
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.Constants
import com.google.firebase.messaging.FirebaseMessaging
import com.heptavators.friendease.data.repository.UserRepository
import com.heptavators.friendease.ui.components.UiState

class LoginViewModel(
    private val repository: UserRepository,
): ViewModel() {
    private val _loginStatus = mutableStateOf<UiState<String>>(UiState.Loading)
    val loginStatus: State<UiState<String>> get() = _loginStatus

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    fun login(email: String, password: String) {
        repository.login(email, password).observeForever {
            when (it) {
                is UiState.Loading -> {
                    _loginStatus.value = UiState.Loading
                    _isLoading.value = true
                }
                is UiState.Success -> {
                    _loginStatus.value = UiState.Success(it.data)
                }
                is UiState.Error -> {
                    _loginStatus.value = UiState.Error(it.errorMessage)
                    _isLoading.value = false
                }
                is UiState.NotLogged -> {
                    _loginStatus.value = UiState.NotLogged
                    _isLoading.value = false
                }
            }
        }
    }


    fun changeDeviceToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(Constants.MessageNotificationKeys.TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result
            // Log and toast
            Log.d(Constants.MessageNotificationKeys.TAG, token)
            repository.changeDeviceToken(token).observeForever {
                when (it) {
                    is UiState.Loading -> {
                        _loginStatus.value = UiState.Loading
                        _isLoading.value = true
                    }
                    is UiState.Success -> {
                        _loginStatus.value = UiState.Success(it.data)
                    }
                    is UiState.Error -> {
                        _loginStatus.value = UiState.Error(it.errorMessage)
                        _isLoading.value = false
                    }
                    is UiState.NotLogged -> {
                        _loginStatus.value = UiState.NotLogged
                        _isLoading.value = false
                    }
                }
            }
        })
    }



}