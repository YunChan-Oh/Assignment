package com.ai.assignment.chat.domain.repository

import com.ai.assignment.chat.domain.Chat
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRepository : JpaRepository<Chat, Long> {
    fun findByThreadUserId(userId: Long, pageable: Pageable): Page<Chat>

    fun findByThreadId(threadId: Long): List<Chat>
}