package com.heptavators.friendease.data.model

import com.google.gson.annotations.SerializedName


data class LoginResponse(

    @field:SerializedName("data")
    val data: User,

    @field:SerializedName("info")
    val info: Info
)

data class User(
    @field:SerializedName("token")
    val token: String,
)

