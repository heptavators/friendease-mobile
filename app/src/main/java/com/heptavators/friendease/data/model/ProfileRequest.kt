package com.heptavators.friendease.data.model

data class ProfileRequest(
    val fullname: String,
    val username: String,
    val bod: String,
    val locationId: String,
    val gender: String,
    val user_preferences: String,
    val tags: List<String>
)