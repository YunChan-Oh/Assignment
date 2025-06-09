package com.ai.assignment.user.domain.repository

import com.ai.assignment.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?

    override fun getById(userId: Long): User =
        findById(userId).orElseThrow { RuntimeException("해당 ID의 유저가 존재하지 않습니다.") }
}