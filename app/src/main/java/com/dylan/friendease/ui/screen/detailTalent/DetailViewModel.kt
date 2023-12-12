package com.dylan.friendease.ui.screen.detailTalent

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dylan.friendease.data.model.DetailTalentData
import com.dylan.friendease.data.repository.TalentRepository
import com.dylan.friendease.ui.components.UiState

class DetailViewModel(
    private val talentRepository: TalentRepository
): ViewModel() {
    private val _talentData = mutableStateOf<UiState<DetailTalentData>>(UiState.Loading)
    val talentData: State<UiState<DetailTalentData>> get() = _talentData

    fun getTalentById(id: String){
        talentRepository.getDetailTalent(id).observeForever {
            when (it) {
                is UiState.Loading -> _talentData.value = UiState.Loading
                is UiState.Success -> _talentData.value = UiState.Success(it.data.data)
                is UiState.Error -> _talentData.value = UiState.Error(it.errorMessage)
                is UiState.NotLogged -> _talentData.value = UiState.NotLogged
            }
        }
    }
}