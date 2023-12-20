package com.heptavators.friendease.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.heptavators.friendease.data.api.ApiService
import com.heptavators.friendease.data.model.ErrorResponse
import com.heptavators.friendease.data.model.ListOrderResponse
import com.heptavators.friendease.data.pref.UserPreference
import com.heptavators.friendease.ui.components.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class OrderRepository(
    private val apiService: ApiService,
    private val userPreference: UserPreference,
    ) {

    fun getAllOrder(): LiveData<UiState<ListOrderResponse>> = liveData(Dispatchers.IO) {
        emit(UiState.Loading)
        try {
            val token = runBlocking {
                userPreference.getUser().first().token
            }
            val response = apiService.getListOrder("Bearer ${token}")
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    emit(UiState.Success(body))
                } else {
                    emit(UiState.Error("Get Talent Failed"))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                if (!errorBody.isNullOrBlank()) {
                    val gson = Gson()
                    val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                    if (response.code() == 401){
                        userPreference.deleteUser()
                        emit(UiState.NotLogged)
                    } else {
                        emit(UiState.Error(errorResponse.info.message.errors[0].message))
                    }
                } else {
                    emit(UiState.Error("Get Talent Failed"))
                }
            }
        } catch (e: Exception) {
            emit(UiState.Error(e.message.toString()))
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: OrderRepository? = null

        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference
        ): OrderRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: OrderRepository(
                    apiService,
                    userPreference,
                )
            }.also { INSTANCE = it }
    }
}