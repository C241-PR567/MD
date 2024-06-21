package com.dicoding.md.data.response

data class SignUpRequest(
    val email: String,
    val name: String,
    val password: String,
    val phone: String
)
