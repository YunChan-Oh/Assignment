package com.ai.assignment.auth.domain

data class Token(
    val accessToken: String,
    val refreshToken: String
)
