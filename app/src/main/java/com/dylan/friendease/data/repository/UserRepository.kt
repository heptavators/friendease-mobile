package com.dylan.friendease.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dylan.friendease.data.api.ApiService
import com.dylan.friendease.data.model.ErrorResponse
import com.dylan.friendease.data.model.Info
import com.dylan.friendease.data.model.LoginRequest
import com.dylan.friendease.data.model.UserResponse
import com.dylan.friendease.data.pref.UserPreference
import com.dylan.friendease.ui.components.UiState
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

class UserRepository(
    private val apiService: ApiService,
    private val userPreference: UserPreference,
) {

    fun login(email: String, password: String): LiveData<UiState<String>> =
        liveData(Dispatchers.IO) {
            emit(UiState.Loading)
            try {
                val response = apiService.login(
                    LoginRequest(
                        email,
                        password
                    ),
                )
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        userPreference.saveUser(body.data)
                        emit(UiState.Success("Success"))
                    } else {
                        emit(UiState.Error("Login Failed"))
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
                        emit(UiState.Error("Login Failed"))
                    }
                }
            } catch (e: Exception) {
                emit(UiState.Error(e.message.toString()))
            }
        }

    fun getProfile(): LiveData<UiState<UserResponse>> =
        liveData(Dispatchers.IO) {
            emit(UiState.Loading)
            try {
                val token = runBlocking {
                    userPreference.getUser().first().token
                }
                val response = apiService.getProfile("Bearer ${token}")
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
                        emit(UiState.Error("Get Profile Failed"))
                    }
                }
            } catch (e: Exception) {
                emit(UiState.Error(e.message.toString()))
            }
        }

    companion object{
        @Volatile
        private var INSTANCE: UserRepository? = null

        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference
        ): UserRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserRepository(
                    apiService,
                    userPreference,
                )
            }.also { INSTANCE = it }
    }
}