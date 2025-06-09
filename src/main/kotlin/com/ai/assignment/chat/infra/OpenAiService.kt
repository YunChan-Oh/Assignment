package com.ai.assignment.chat.infra

interface OpenAiService {
    fun generateAnswer(messages: List<String>, model: String?): String
}