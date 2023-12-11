package com.dylan.friendease.data.api

import com.dylan.friendease.data.model.LoginRequest
import com.dylan.friendease.data.model.LoginResponse
import com.dylan.friendease.data.model.TalentResponse
import com.dylan.friendease.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("auth/login")
    suspend fun login(
        @Body requestBody: LoginRequest
    ): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @GET("auth/profile")
    suspend fun getProfile(
        @Header("Authorization") token: String,
    ): Response<UserResponse>

    @Headers("Content-Type: application/json")
    @GET("talent")
    suspend fun getAllTalent(
        @Header("Authorization") token: String,
    ): Response<TalentResponse>
}