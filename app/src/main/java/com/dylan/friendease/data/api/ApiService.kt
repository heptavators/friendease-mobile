package com.dylan.friendease.data.api

import com.dylan.friendease.data.model.DetailTalentResponse
import com.dylan.friendease.data.model.LoginRequest
import com.dylan.friendease.data.model.LoginResponse
import com.dylan.friendease.data.model.NotificationResponse
import com.dylan.friendease.data.model.TalentResponse
import com.dylan.friendease.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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
        @Query("fullname") fullname: String,
    ): Response<TalentResponse>

    @Headers("Content-Type: application/json")
    @GET("talent/{idTalent}")
    suspend fun getDetailTalent(
        @Header("Authorization") token: String,
        @Path("idTalent") idTalent: String,
    ): Response<DetailTalentResponse>

    @Headers("Content-Type: application/json")
    @GET("notification/user")
    suspend fun getNotification(
        @Header("Authorization") token: String,
    ): Response<NotificationResponse>
}