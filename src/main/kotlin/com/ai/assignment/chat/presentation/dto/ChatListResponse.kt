package com.ai.assignment.chat.presentation.dto

data class ChatListResponse(
    val content: List<ThreadResponse>,
    val totalElements: Long,
    val totalPages: Int,
    val number: Int,
    val size: Int
)