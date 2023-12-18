package com.example.capstone.data.response

import com.google.gson.annotations.SerializedName

data class ResponseAuth(

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
