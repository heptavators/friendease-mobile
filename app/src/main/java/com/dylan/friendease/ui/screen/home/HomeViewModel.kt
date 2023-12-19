package com.dylan.friendease.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dylan.friendease.data.model.TalentData
import com.dylan.friendease.data.model.TalentResponse
import com.dylan.friendease.data.repository.TalentRepository
import com.dylan.friendease.ui.components.UiState

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