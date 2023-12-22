package com.heptavators.friendease.data.model

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("data")
	val data: DataProfile,

	@field:SerializedName("info")
	val info: Info
)

data class DataProfile(

	@field:SerializedName("gender")
	val gender: String,

	@field:SerializedName("roles")
	val roles: String,

	@field:SerializedName("bio")
	val bio: String,

	@field:SerializedName("avatar")
	val avatar: String,

	@field:SerializedName("authId")
	val authId: String,

	@field:SerializedName("tags")
	val tags: List<TagsItem?>,

	@field:SerializedName("bod")
	val bod: String,

	@field:SerializedName("device_token")
	val deviceToken: String,

	@field:SerializedName("phone_number")
	val phoneNumber: String,

	@field:SerializedName("location")
	val location: Location,

	@field:SerializedName("fullname")
	val fullname: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("user_preferences")
	val userPreferences: String
)

data class TagAuth(

	@field:SerializedName("tagAuthId")
	val tagAuthId: String,

	@field:SerializedName("tagId")
	val tagId: String,

	@field:SerializedName("authId")
	val authId: String
)

data class TagsItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("tagId")
	val tagId: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("TagAuth")
	val tagAuth: TagAuth,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
