package com.heptavators.friendease.ui.screen.schedule

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.heptavators.friendease.data.model.ListOrderResponse
import com.heptavators.friendease.data.repository.OrderRepository
import com.heptavators.friendease.ui.components.UiState

class ScheduleViewModel(
    private val orderRepository: OrderRepository
): ViewModel(){
    private val _orderData = mutableStateOf<UiState<ListOrderResponse>>(UiState.Loading)
    val orderData: State<UiState<ListOrderResponse>> get() = _orderData

    fun getOrder() {
        orderRepository.getAllOrder().observeForever {
            when (it) {
                is UiState.Loading -> _orderData.value = UiState.Loading
                is UiState.Success -> _orderData.value = UiState.Success(it.data)
                is UiState.Error -> _orderData.value = UiState.Error(it.errorMessage)
                is UiState.NotLogged -> _orderData.value = UiState.NotLogged
            }
        }
    }
}