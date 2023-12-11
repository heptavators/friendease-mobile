package com.dylan.friendease.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dylan.friendease.data.api.ApiService
import com.dylan.friendease.data.model.ErrorResponse
import com.dylan.friendease.data.model.TalentResponse
import com.dylan.friendease.data.pref.UserPreference
import com.dylan.friendease.ui.components.UiState
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class TalentRepository(
    private val apiService: ApiService,
    private val userPreference: UserPreference,
    ) {

    fun getAllTalent(): LiveData<UiState<TalentResponse>> = liveData(Dispatchers.IO) {
            emit(UiState.Loading)
            try {
                val token = runBlocking {
                    userPreference.getUser().first().token
                }
                val response = apiService.getAllTalent("Bearer ${token}")
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        emit(UiState.Success(body))
                    } else {
                        emit(UiState.Error("Get Profile Failed"))
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    if (!errorBody.isNullOrBlank()) {
                        val gson = Gson()
                        val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                        if (errorResponse.code == 401) {
                            emit(UiState.NotLogged)
                        } else {
                            emit(UiState.Error(errorResponse.message))
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
        private var INSTANCE: TalentRepository? = null

        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference
        ): TalentRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: TalentRepository(
                    apiService,
                    userPreference,
                )
            }.also { INSTANCE = it }
    }
}