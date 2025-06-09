package com.ai.assignment.auth.service.implementation

import com.ai.assignment.common.config.JwtCredentials
import com.ai.assignment.user.domain.User
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthValidator(
    private val jwtCredentials: JwtCredentials,
    private val passwordEncoder: PasswordEncoder
) {
    fun validatePassword(password: String, user: User) {
        if (!passwordEncoder.matches(password, user.password)) {
            throw RuntimeException("비밀번호가 올바르지 않습니다.")
        }
    }

    fun shouldRefreshTokenValid(refreshToken: String) {
        try {
            Jwts.parser()
                .verifyWith(jwtCredentials.secretKey)
                .build()
                .parse(refreshToken)
        } catch (e: ExpiredJwtException) {
            throw RuntimeException("토큰이 만료되었습니다.")
        } catch (e: JwtException) {
            throw RuntimeException("토큰이 유효하지 않습니다.")
        }
    }
}