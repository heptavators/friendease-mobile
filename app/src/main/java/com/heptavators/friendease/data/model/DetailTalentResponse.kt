package com.heptavators.friendease.data.model

import com.google.gson.annotations.SerializedName

data class DetailTalentResponse(

	@field:SerializedName("data")
	val data: DetailTalentData,

	@field:SerializedName("info")
	val info: Info
)

data class DetailTalentData(

	@field:SerializedName("authId")
	val authId: String,
	@field:SerializedName("talentId")
	val talentId: String,

	@field:SerializedName("description")
	val description: String?,

	@field:SerializedName("verified_status")
	val verifiedStatus: String,


	@field:SerializedName("rating")
	val rating: Any,

	@field:SerializedName("highlight")
	val highlight: List<HighlightItem>,

	@field:SerializedName("auth")
	val auth: AuthDetail,

	@field:SerializedName("tags")
	val tags: List<Tags>,


)

data class Tags(
	@field:SerializedName("tagId")
	val tagId: String,

	@field:SerializedName("name")
	val name: String,
)
data class AuthDetail(

	@field:SerializedName("gender")
	val gender: String,

	@field:SerializedName("bod")
	val bod: String,

	@field:SerializedName("roles")
	val roles: String,

	@field:SerializedName("bio")
	val bio: String,

	@field:SerializedName("phone_number")
	val phoneNumber: String,

	@field:SerializedName("location")
	val location: LocationDetail,

	@field:SerializedName("fullname")
	val fullname: String,

	@field:SerializedName("avatar")
	val avatar: String,

	@field:SerializedName("authId")
	val authId: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("status")
	val status: String
)

data class LocationDetail(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("province")
	val province: String,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("locationId")
	val locationId: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

data class HighlightItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("talentId")
	val talentId: String,

	@field:SerializedName("highlightURL")
	val highlightURL: String,

	@field:SerializedName("highlightId")
	val highlightId: String,

	@field:SerializedName("highlightNameFile")
	val highlightNameFile: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
