package com.heptavators.friendease.ui.screen.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heptavators.friendease.data.model.UserData
import com.heptavators.friendease.data.repository.UserRepository
import com.heptavators.friendease.ui.components.UiState
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: UserRepository,
): ViewModel() {
    private val _profileData = mutableStateOf<UiState<UserData>>(UiState.Loading)
    val profileData: State<UiState<UserData>> get() = _profileData

    fun user() {
        repository.getProfile().observeForever {
            when (it) {
                is UiState.Loading -> _profileData.value = UiState.Loading
                is UiState.Success -> _profileData.value = UiState.Success(it.data.data)
                is UiState.Error -> _profileData.value = UiState.Error(it.errorMessage)
                is UiState.NotLogged -> _profileData.value = UiState.NotLogged
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}