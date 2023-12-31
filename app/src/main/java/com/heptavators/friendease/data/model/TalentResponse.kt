package com.heptavators.friendease.data.model

import com.google.gson.annotations.SerializedName

data class TalentResponse(

    @field:SerializedName("data")
    val data: List<TalentData>,

    @field:SerializedName("info")
    val info: Info
)

data class TalentData(
    @SerializedName("talentId")
    val talentId: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("verified_status")
    val verifiedStatus: String,
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("LocationTalent")
    val location: LocationTalent,
    @SerializedName("auth")
    val auth: Auth,
    @SerializedName("highlights")
    val highlights: List<Highlight>
)


data class LocationTalent(
    @SerializedName("locationId")
    val locationId: String,
    @SerializedName("province")
    val province: String,
    @SerializedName("city")
    val city: String
)

data class Auth(
    @SerializedName("authId")
    val authId: String,
    @SerializedName("fullname")
    val fullname: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("tags")
    val tags: List<Tags>
)

data class Tags(
    @SerializedName("tagId")
    val tagId: String,
    @SerializedName("name")
    val name: String,
)

data class Highlight(
    @SerializedName("highlightId")
    val highlightId: String,
    @SerializedName("highlightURL")
    val highlightURL: String
)