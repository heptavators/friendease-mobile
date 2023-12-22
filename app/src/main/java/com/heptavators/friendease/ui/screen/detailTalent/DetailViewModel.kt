package com.heptavators.friendease.ui.screen.detailTalent

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.heptavators.friendease.data.model.DetailTalentData
import com.heptavators.friendease.data.model.OrderItem
import com.heptavators.friendease.data.model.OrderResponse
import com.heptavators.friendease.data.repository.TalentRepository
import com.heptavators.friendease.ui.components.UiState

class DetailViewModel(
    private val talentRepository: TalentRepository
): ViewModel() {
    private val _talentData = mutableStateOf<UiState<DetailTalentData>>(UiState.Loading)
    val talentData: State<UiState<DetailTalentData>> get() = _talentData

    private val _orderData = mutableStateOf<UiState<OrderItem>>(UiState.Loading)
    val orderData: State<UiState<OrderItem>> get() = _orderData

    private val _orderStatus = mutableStateOf<UiState<String>>(UiState.Loading)
    val orderStatus: State<UiState<String>> get() = _orderStatus



    fun getTalentById(id: String){
        talentRepository.getDetailTalent(id).observeForever {
            when (it) {
                is UiState.Loading -> _talentData.value = UiState.Loading
                is UiState.Success -> _talentData.value = UiState.Success(it.data.data.talent)
                is UiState.Error -> _talentData.value = UiState.Error(it.errorMessage)
                is UiState.NotLogged -> _talentData.value = UiState.NotLogged
            }
        }
    }


    fun orderTalent(id: String, name: String, type: String, start_hour: String, end_hour: String, date: String){
        talentRepository.orderTalent(id, name, type, start_hour, end_hour, date).observeForever {
            when (it) {
                is UiState.Loading -> {
                    _orderStatus.value = UiState.Loading
                }
                is UiState.Success -> {
                    _orderStatus.value = UiState.Success("success")
                    _orderData.value = UiState.Success(it.data.data)
                }
                is UiState.Error -> {
                    _orderStatus.value = UiState.Error(it.errorMessage)
                }
                is UiState.NotLogged -> {
                    _orderStatus.value = UiState.NotLogged
                }
            }
        }
    }
}