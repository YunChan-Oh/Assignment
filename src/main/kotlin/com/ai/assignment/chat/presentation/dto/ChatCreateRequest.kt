package com.ai.assignment.chat.presentation.dto

data class ChatCreateRequest(
    val question: String,
    val isStreaming: Boolean = false,
    val model: String? = null
)