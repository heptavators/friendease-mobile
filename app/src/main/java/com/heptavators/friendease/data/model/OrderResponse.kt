package com.heptavators.friendease.data.model

import com.google.gson.annotations.SerializedName

data class ListOrderResponse(

	@field:SerializedName("data")
	val data: List<OrderItem>,

	@field:SerializedName("info")
	val info: Info
)
data class OrderResponse(

	@field:SerializedName("data")
	val data: OrderItem,

	@field:SerializedName("info")
	val info: Info
)

data class OrderItem(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("transaction_status")
	val transactionStatus: String,

	@field:SerializedName("orderId")
	val orderId: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("token")
	val token: String? = "",

	@field:SerializedName("total_hour")
	val totalHour: Int,

	@field:SerializedName("order_status")
	val orderStatus: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("payment_type")
	val paymentType: Any,

	@field:SerializedName("talentId")
	val talentId: String,

	@field:SerializedName("total_amount")
	val totalAmount: Int,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("start_hour")
	val startHour: String,

	@field:SerializedName("customerId")
	val customerId: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("transaction_time")
	val transactionTime: Any,

	@field:SerializedName("end_hour")
	val endHour: String,

	@field:SerializedName("redirect_url")
	val redirectUrl: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

