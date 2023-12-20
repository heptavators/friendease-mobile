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

data class Meta(

    @field:SerializedName("totalItems")
    val totalItems: Int? = null,

    @field:SerializedName("totalPage")
    val totalPage: Int? = null,

    @field:SerializedName("itemsPerPage")
    val itemsPerPage: Int? = null,

    @field:SerializedName("currentPage")
    val currentPage: Int? = null
)
