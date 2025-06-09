package com.ai.assignment.auth.util

import com.ai.assignment.common.config.JwtCredentials
import org.springframework.stereotype.Component
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts

@Component
class JwtParser(
    private val jwtCredentials: JwtCredentials
) {

    companion object {
        const val ID = "id"
    }

    fun getIdFromJwt(jwt: String): Long {
        return try {
            Jwts.parser()
                    .verifyWith(jwtCredentials.secretKey)
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload()
                    .toString()
                    .toLong()
        } catch (e: ExpiredJwtException) {
            throw RuntimeException("토큰이 만료되었습니다.")
        } catch (e: JwtException) {
            throw RuntimeException("토큰이 유효하지 않습니다.")
        }
    }
}