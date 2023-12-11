package com.dylan.friendease.ui.screen.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dylan.friendease.data.repository.UserRepository
import com.dylan.friendease.ui.components.UiState

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
}