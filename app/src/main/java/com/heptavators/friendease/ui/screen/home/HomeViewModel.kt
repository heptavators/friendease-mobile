package com.heptavators.friendease.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.heptavators.friendease.data.model.TalentResponse
import com.heptavators.friendease.data.repository.TalentRepository
import com.heptavators.friendease.ui.components.UiState

class HomeViewModel(
    private val talentRepository: TalentRepository
): ViewModel() {
    private val _talentData = mutableStateOf<UiState<TalentResponse>>(UiState.Loading)
    val talentData: State<UiState<TalentResponse>> get() = _talentData

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

}