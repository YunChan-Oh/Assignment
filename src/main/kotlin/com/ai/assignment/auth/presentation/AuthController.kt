package com.ai.assignment.auth.presentation

import com.ai.assignment.auth.presentation.dto.AccessTokenRequest
import com.ai.assignment.auth.presentation.dto.RefreshTokenRequest
import com.ai.assignment.auth.presentation.dto.TokenResponse
import com.ai.assignment.auth.service.CommandAuthService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: CommandAuthService
) {

    @PostMapping("/login")
    fun login(@RequestBody accessTokenRequest: AccessTokenRequest): TokenResponse {
        return TokenResponse.from(
            authService.login(accessTokenRequest)
        )
    }

    @PostMapping("/refresh")
    fun refreshAccessToken(@RequestBody refreshToken: RefreshTokenRequest): TokenResponse {
        return TokenResponse.from(
            authService.refresh(refreshToken.refreshToken)
        )
    }
}