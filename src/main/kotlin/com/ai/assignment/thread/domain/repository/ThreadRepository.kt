package com.ai.assignment.thread.domain.repository

import com.ai.assignment.thread.domain.Thread
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface ThreadRepository : JpaRepository<Thread, Long> {
    fun findByUserIdAndCreatedAtAfterOrderByCreatedAtDesc(
        userId: Long,
        createdAt: LocalDateTime
    ): List<Thread>

    fun findByUserId(userId: Long): List<Thread>
}