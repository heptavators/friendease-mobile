package com.dylan.friendease.data.model

data class OrderRequest (
    val talentId: String,
    val customerId: String,
    val activityName: String,
    val status: String
)