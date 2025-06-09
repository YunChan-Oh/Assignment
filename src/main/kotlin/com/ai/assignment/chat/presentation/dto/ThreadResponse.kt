package com.ai.assignment.chat.presentation.dto

import java.time.LocalDateTime

data class ThreadResponse(
    val threadId: Long,
    val createdAt: LocalDateTime,
    val chats: List<ChatResponse>
)