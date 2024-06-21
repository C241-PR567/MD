package com.dicoding.md.data.response

import com.google.gson.annotations.SerializedName

data class ResetPasswordRequest(

    @field:SerializedName("email")
    val email: String
)
