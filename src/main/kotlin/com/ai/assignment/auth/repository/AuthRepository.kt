package com.ai.assignment.auth.repository

import com.ai.assignment.user.domain.User
import org.springframework.stereotype.Repository
import org.springframework.web.context.annotation.RequestScope

@Repository
@RequestScope
class AuthRepository {

    private var currentUser: User? = null

    fun getCurrentUser(): User {
        return currentUser ?: throw RuntimeException("유저가 로그인하지 않았습니다.")
    }

    fun getNullableCurrentUser(): User? {
        return currentUser
    }

    fun updateCurrentUser(currentUser: User?) {
        this.currentUser = currentUser
    }
}