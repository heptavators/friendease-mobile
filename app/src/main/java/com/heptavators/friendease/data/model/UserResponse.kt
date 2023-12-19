package com.heptavators.friendease.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @field:SerializedName("data")
    val data: UserData,

    @field:SerializedName("info")
    val info: Info
)

data class UserData(
    @SerializedName("authId")
    val authId: String,
    @SerializedName("fullname")
    val fullname: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("bio")
    val bio: String,
    @SerializedName("bod")
    val bod: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("roles")
    val roles: String,
    @SerializedName("device_token")
    val deviceToken: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("location")
    val location: Location
)

data class Location(
    @SerializedName("locationId")
    val locationId: String,
    @SerializedName("province")
    val province: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)



