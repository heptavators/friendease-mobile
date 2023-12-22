package com.heptavators.friendease.data.model

import com.google.gson.annotations.SerializedName

data class LocationResponse(

	@field:SerializedName("data")
	val data: List<DataLocation>,

	@field:SerializedName("info")
	val info: Info? = null
)

data class DataLocation(

	@field:SerializedName("province")
	val province: String,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("locationId")
	val locationId: String
)