package com.ai.assignment.user.service.implementation

import com.ai.assignment.user.domain.User
import com.ai.assignment.user.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserReader(
    private val userRepository: UserRepository
) {
    fun readByEmail(email: String): User =
        userRepository.findByEmail(email)
            ?: RuntimeException("해당 email의 유저가 존재하지 않습니다.")

    fun read(userId: Long): User =
        userRepository.getById(userId)

    fun readAll(): List<User> =
        userRepository.findAll()
}