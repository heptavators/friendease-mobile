package com.heptavators.friendease.ui.screen.scheduleDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.heptavators.friendease.data.model.DataOrder
import com.heptavators.friendease.data.model.DetailTalentData
import com.heptavators.friendease.data.repository.OrderRepository
import com.heptavators.friendease.ui.components.UiState

class ScheduleDetailViewModel(
    private val orderRepository: OrderRepository
): ViewModel(){

    private val _orderData = mutableStateOf<UiState<DataOrder>>(UiState.Loading)
    val orderData: State<UiState<DataOrder>> get() = _orderData


    fun getOrderById(id: String){
        orderRepository.getDetailOrder(id).observeForever {
            when (it) {
                is UiState.Loading -> _orderData.value = UiState.Loading
                is UiState.Success -> _orderData.value = UiState.Success(it.data.data)
                is UiState.Error -> _orderData.value = UiState.Error(it.errorMessage)
                is UiState.NotLogged -> _orderData.value = UiState.NotLogged
            }
        }
    }
}