package com.dicoding.md.data.response

import com.google.gson.annotations.SerializedName

data class DetailDiseaseResponse(

	@field:SerializedName("msg")
	val msg: String,

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("success")
	val success: Boolean
)

data class Data(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("penanganan")
	val penanganan: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("penyebab")
	val penyebab: String,

	@field:SerializedName("description")
	val description: String
)
