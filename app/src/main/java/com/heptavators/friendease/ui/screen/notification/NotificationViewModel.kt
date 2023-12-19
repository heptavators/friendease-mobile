package com.heptavators.friendease.ui.screen.notification

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.heptavators.friendease.data.model.NotificationResponse
import com.heptavators.friendease.data.repository.NotificationRepository
import com.heptavators.friendease.ui.components.UiState

class NotificationViewModel(
    private val notificationRepository: NotificationRepository
): ViewModel() {
    private val _notificationData = mutableStateOf<UiState<NotificationResponse>>(UiState.Loading)
    val notificationData: State<UiState<NotificationResponse>> get() = _notificationData

    fun getNotification() {
        notificationRepository.getNotification().observeForever {
            when (it) {
                is UiState.Loading -> _notificationData.value = UiState.Loading
                is UiState.Success -> _notificationData.value = UiState.Success(it.data)
                is UiState.Error -> _notificationData.value = UiState.Error(it.errorMessage)
                is UiState.NotLogged -> _notificationData.value = UiState.NotLogged
            }
        }
    }
}