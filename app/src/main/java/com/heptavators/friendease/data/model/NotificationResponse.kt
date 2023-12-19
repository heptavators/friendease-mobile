package com.heptavators.friendease.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class NotificationResponse(

    @field:SerializedName("data")
    val data: List<NotificationData>,

    @field:SerializedName("info")
    val info: Info
)

data class NotificationData(
    @SerializedName("notificationId")
    val notificationId: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("authId")
    val authId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("createdAt")
    val createdAt: Date,
    @SerializedName("updatedAt")
    val updatedAt: Date
)
