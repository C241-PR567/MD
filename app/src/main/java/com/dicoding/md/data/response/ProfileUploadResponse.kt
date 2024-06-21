package com.dicoding.md.data.response

import com.google.gson.annotations.SerializedName

data class ProfileUploadResponse(

	@field:SerializedName("fileName")
	val fileName: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("status")
	val status: String
)
