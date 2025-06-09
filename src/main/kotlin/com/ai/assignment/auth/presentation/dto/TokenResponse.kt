package com.ai.assignment.auth.presentation.dto

import com.ai.assignment.auth.domain.Token

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun from(token: Token): TokenResponse {
            return TokenResponse(
                accessToken = token.accessToken,
                refreshToken = token.refreshToken
            )
        }
    }
}