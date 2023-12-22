package com.heptavators.friendease.data.model

import com.google.gson.annotations.SerializedName

data class TagsResponse(

	@field:SerializedName("data")
	val data: List<DataTags>,

	@field:SerializedName("info")
	val info: Info
)

data class DataTags(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("tagId")
	val tagId: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
