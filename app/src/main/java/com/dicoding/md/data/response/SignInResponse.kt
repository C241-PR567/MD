package com.dicoding.md.data.response

import com.google.gson.annotations.SerializedName

data class SignInResponse(

    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("msg")
    val msg: String,

    @field:SerializedName("data")
    val data: UserData
)

data class UserData(
    @field:SerializedName("uid")
    val uid: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("name")
    val name: String
)
