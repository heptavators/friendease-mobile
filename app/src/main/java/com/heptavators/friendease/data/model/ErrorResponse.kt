package com.heptavators.friendease.data.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

	@field:SerializedName("data")
	val data: Any,

	@field:SerializedName("info")
	val info: InfoError
)

data class Message(
	@field:SerializedName("errors")
	val errors: List<ErrorsItem>
)

data class ErrorsItem(

	@field:SerializedName("error")
	val error: String,

	@field:SerializedName("message")
	val message: String
)

data class InfoError(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("meta")
	val meta: Any,

	@field:SerializedName("message")
	val message: Message
)
