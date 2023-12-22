package com.heptavators.friendease

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heptavators.friendease.data.model.LoginResponse
import com.heptavators.friendease.data.model.RegisterResponse
import com.heptavators.friendease.data.model.User
import com.heptavators.friendease.data.model.UserData
import com.heptavators.friendease.data.repository.UserRepository
import com.heptavators.friendease.ui.components.UiState
import kotlinx.coroutines.launch

class AppViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    private val _isHaveToken: MutableState<UiState<Boolean>> = mutableStateOf(UiState.Loading)
    val isHaveToken: MutableState<UiState<Boolean>>
        get() = _isHaveToken

    private val _profileData = mutableStateOf<UiState<UserData>>(UiState.Loading)
    val profileData: State<UiState<UserData>> get() = _profileData


    fun validateToken(){
        viewModelScope.launch {
            _isHaveToken.value = UiState.Success(userRepository.getToken().isNotEmpty())
        }
    }


    fun user() {
        userRepository.getProfile().observeForever {
            when (it) {
                is UiState.Loading -> _profileData.value = UiState.Loading
                is UiState.Success -> _profileData.value = UiState.Success(it.data.data)
                is UiState.Error -> _profileData.value = UiState.Error(it.errorMessage)
                is UiState.NotLogged -> _profileData.value = UiState.NotLogged
            }
        }
    }

}