package com.dylan.friendease.data.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @field:SerializedName("id")
    val id: String,
)