package com.dicoding.md.data.response

import com.google.gson.annotations.SerializedName

data class DiseaseResponse(

	@field:SerializedName("diseases")
	val diseases: List<DiseasesItem>
)

data class DiseasesItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: String
)
