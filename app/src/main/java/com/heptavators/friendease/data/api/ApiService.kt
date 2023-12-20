package com.heptavators.friendease.data.api

import com.heptavators.friendease.data.model.DetailTalentResponse
import com.heptavators.friendease.data.model.ListOrderResponse
import com.heptavators.friendease.data.model.LoginRequest
import com.heptavators.friendease.data.model.LoginResponse
import com.heptavators.friendease.data.model.NotificationResponse
import com.heptavators.friendease.data.model.OrderRequest
import com.heptavators.friendease.data.model.OrderResponse
import com.heptavators.friendease.data.model.TalentResponse
import com.heptavators.friendease.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
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
    ): Response<TalentResponse>

    @Headers("Content-Type: application/json")
    @GET("talent")
    suspend fun getTalent(
        @Header("Authorization") token: String,
        @Query("fullname") fullname: String,
    ): Response<TalentResponse>

    @Headers("Content-Type: application/json")
    @POST("create-order/{idTalent}")
    suspend fun order(
        @Header("Authorization") token: String,
        @Body requestBody: OrderRequest,
        @Path("idTalent") idTalent: String,
        ): Response<OrderResponse>

    @Headers("Content-Type: application/json")
    @GET("order/user")
    suspend fun getListOrder(
        @Header("Authorization") token: String,
    ): Response<ListOrderResponse>

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