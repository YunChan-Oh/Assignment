package com.ai.assignment.auth.service

import com.ai.assignment.auth.domain.Token
import com.ai.assignment.auth.presentation.dto.AccessTokenRequest
import com.ai.assignment.auth.service.implementation.AuthReader
import com.ai.assignment.auth.service.implementation.AuthValidator
import com.ai.assignment.auth.service.implementation.TokenProvider
import com.ai.assignment.auth.util.BearerTokenExtractor
import com.ai.assignment.user.service.implementation.UserReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CommandAuthService(
    private val tokenProvider: TokenProvider,
    private val authReader: AuthReader,
    private val authValidator: AuthValidator,
    private val userReader: UserReader
) {
    fun login(accessTokenRequest: AccessTokenRequest): Token {
        val user = userReader.readByEmail(accessTokenRequest.email)
        authValidator.validatePassword(accessTokenRequest.password, user)
        return tokenProvider.createNewTokens(user)
    }

    fun refresh(bearer: String): Token {
        val refreshToken = BearerTokenExtractor.extract(bearer)
        authValidator.shouldRefreshTokenValid(refreshToken)

        val userId: Long = authReader.getIdFromJwt(refreshToken)
        val user = userReader.read(userId)
        val accessToken = tokenProvider.createAccessToken(user)

        return Token(accessToken, refreshToken)
    }
}