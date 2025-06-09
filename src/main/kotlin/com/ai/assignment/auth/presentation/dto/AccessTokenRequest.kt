package com.ai.assignment.auth.presentation.dto

data class AccessTokenRequest(
    val email: String,
    val password: String
)