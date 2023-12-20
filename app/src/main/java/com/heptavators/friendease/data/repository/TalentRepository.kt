package com.heptavators.friendease.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.heptavators.friendease.data.api.ApiService
import com.heptavators.friendease.data.model.DetailTalentResponse
import com.heptavators.friendease.data.model.ErrorResponse
import com.heptavators.friendease.data.model.TalentResponse
import com.heptavators.friendease.data.pref.UserPreference
import com.heptavators.friendease.ui.components.UiState
import com.google.gson.Gson
import com.heptavators.friendease.data.model.LoginRequest
import com.heptavators.friendease.data.model.OrderRequest
import com.heptavators.friendease.data.model.OrderResponse
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

    fun getTalent(fullname: String = ""): LiveData<UiState<TalentResponse>> = liveData(Dispatchers.IO) {
        emit(UiState.Loading)
        try {
            val token = runBlocking {
                userPreference.getUser().first().token
            }
            val response = apiService.getTalent("Bearer ${token}", fullname)
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


    fun getDetailTalent(idTalent: String): LiveData<UiState<DetailTalentResponse>> = liveData(Dispatchers.IO) {
        emit(UiState.Loading)
        try {
            val token = runBlocking {
                userPreference.getUser().first().token
            }
            val response = apiService.getDetailTalent("Bearer ${token}", idTalent)
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

    fun orderTalent(id: String, name: String, type: String, start_hour: String, end_hour: String, date: String): LiveData<UiState<OrderResponse>> =
        liveData(Dispatchers.IO) {
            emit(UiState.Loading)
            try {
                val token = runBlocking {
                    userPreference.getUser().first().token
                }
                val response = apiService.order(
                    token  = "Bearer ${token}",
                    requestBody = OrderRequest(
                        name,
                        type,
                        start_hour,
                        end_hour,
                        date,
                    ),
                    idTalent = id,
                )
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        emit(UiState.Success(body))
                    } else {
                        emit(UiState.Error("Login Failed"))
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    if (!errorBody.isNullOrBlank()) {
                        val gson = Gson()
                        val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                        emit(UiState.Error(errorResponse.info.message.errors[0].message))
                    } else {
                        emit(UiState.Error("Login Failed"))
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