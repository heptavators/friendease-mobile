package com.heptavators.friendease.data.model


data class OrderRequest(
    val name: String,
    val type: String,
    val start_hour: String,
    val end_hour: String,
    val date: String,
)