package com.heptavators.friendease.data.model

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("info")
	val info: Info? = null
)

data class Data(

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("roles")
	val roles: String? = null,

	@field:SerializedName("bio")
	val bio: Any? = null,

	@field:SerializedName("avatar")
	val avatar: Any? = null,

	@field:SerializedName("authId")
	val authId: String,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("locationId")
	val locationId: Any? = null,

	@field:SerializedName("bod")
	val bod: Any? = null,

	@field:SerializedName("device_token")
	val deviceToken: Any? = null,

	@field:SerializedName("phone_number")
	val phoneNumber: Any? = null,

	@field:SerializedName("fullname")
	val fullname: Any? = null,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("username")
	val username: Any? = null,

	@field:SerializedName("user_preferences")
	val userPreferences: Any? = null
)