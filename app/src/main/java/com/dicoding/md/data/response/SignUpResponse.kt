package com.dicoding.md.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SignUpResponse(

	@field:SerializedName("msg")
	@Expose
	val msg: String,

	@field:SerializedName("success")
	@Expose
	val success: Boolean
)


