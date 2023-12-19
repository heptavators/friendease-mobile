package com.heptavators.friendease.data.model

import com.google.gson.annotations.SerializedName

data class Info(

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("meta")
    val meta: Any? = null,

    @field:SerializedName("message")
    val message: String? = null
)