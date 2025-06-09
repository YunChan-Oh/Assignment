package com.ai.assignment.auth.service.implementation

import com.ai.assignment.auth.repository.AuthRepository
import com.ai.assignment.auth.util.JwtParser
import com.ai.assignment.user.domain.User
import org.springframework.stereotype.Service

@Service
class AuthReader(
    private val authRepository: AuthRepository,
    private val jwtParser: JwtParser
) {

    fun getIdFromJwt(token: String): Long {
        return jwtParser.getIdFromJwt(token)
    }

    fun getCurrentUser(): User {
        return authRepository.getCurrentUser()
    }

    fun getNullableCurrentUser(): User? {
        return authRepository.getNullableCurrentUser()
    }
}