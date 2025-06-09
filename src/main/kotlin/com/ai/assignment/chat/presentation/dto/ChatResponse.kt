package com.ai.assignment.chat.presentation.dto

import java.time.LocalDateTime

data class ChatResponse(
    val id: Long,
    val question: String,
    val answer: String,
    val createdAt: LocalDateTime
)
