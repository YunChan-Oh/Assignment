package com.ai.assignment.auth.service.implementation

import com.ai.assignment.auth.repository.AuthRepository
import com.ai.assignment.user.domain.User
import org.springframework.stereotype.Service

@Service
class AuthUpdater(
    private val authRepository: AuthRepository
) {

    fun updateCurrentUser(user: User?) {
        authRepository.updateCurrentUser(user)
    }
}