package com.heptavators.friendease.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.heptavators.friendease.data.model.TalentResponse
import com.heptavators.friendease.data.model.UserData
import com.heptavators.friendease.data.repository.TalentRepository
import com.heptavators.friendease.data.repository.UserRepository
import com.heptavators.friendease.ui.components.UiState

class HomeViewModel(
    private val talentRepository: TalentRepository,
    private val userRepository: UserRepository
): ViewModel() {
    private val _talentData = mutableStateOf<UiState<TalentResponse>>(UiState.Loading)
    val talentData: State<UiState<TalentResponse>> get() = _talentData
    private val _profileData = mutableStateOf<UiState<UserData>>(UiState.Loading)
    val profileData: State<UiState<UserData>> get() = _profileData

    fun getAllTalent() {
        talentRepository.getAllTalent().observeForever {
            when (it) {
                is UiState.Loading -> _talentData.value = UiState.Loading
                is UiState.Success -> _talentData.value = UiState.Success(it.data)
                is UiState.Error -> _talentData.value = UiState.Error(it.errorMessage)
                is UiState.NotLogged -> _talentData.value = UiState.NotLogged
            }
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