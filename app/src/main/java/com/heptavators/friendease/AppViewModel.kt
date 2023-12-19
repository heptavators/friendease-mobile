package com.heptavators.friendease

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heptavators.friendease.data.repository.UserRepository
import com.heptavators.friendease.ui.components.UiState
import kotlinx.coroutines.launch

class AppViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    private val _isHaveToken: MutableState<UiState<Boolean>> = mutableStateOf(UiState.Loading)
    val isHaveToken: MutableState<UiState<Boolean>>
        get() = _isHaveToken

    fun validateToken(){
        viewModelScope.launch {
            _isHaveToken.value = UiState.Success(userRepository.getToken().isNotEmpty())
        }
    }
}