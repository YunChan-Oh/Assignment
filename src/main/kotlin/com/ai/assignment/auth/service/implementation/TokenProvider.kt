package com.ai.assignment.auth.service.implementation

import com.ai.assignment.auth.domain.Token
import com.ai.assignment.common.config.JwtCredentials
import com.ai.assignment.user.domain.User
import java.util.Date
import org.springframework.stereotype.Service
import io.jsonwebtoken.Jwts

@Service
class TokenProvider(
    private val jwtCredentials: JwtCredentials
) {

    fun createNewTokens(user: User): Token {
        return Token(
            accessToken = createAccessToken(user),
            refreshToken = createRefreshToken(user)
        )
    }

    fun createAccessToken(user: User): String {
        return createToken(user, jwtCredentials.accessTokenExpirationTime)
    }

    private fun createRefreshToken(user: User): String {
        return createToken(user, jwtCredentials.refreshTokenExpirationTime)
    }

    private fun createToken(user: User, expireLength: Long): String {
        val now = Date()
        val expiration = Date(System.currentTimeMillis() + expireLength)

        return Jwts.builder()
            .claim("id", user.id)
            .expiration(expiration)
            .signWith(jwtCredentials.secretKey)
            .compact()
    }
}