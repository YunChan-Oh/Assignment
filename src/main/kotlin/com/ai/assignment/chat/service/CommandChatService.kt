package com.ai.assignment.chat.service

import com.ai.assignment.auth.service.implementation.AuthReader
import com.ai.assignment.chat.domain.repository.ChatRepository
import com.ai.assignment.chat.infra.OpenAiService
import com.ai.assignment.chat.presentation.dto.ChatCreateRequest
import com.ai.assignment.chat.presentation.dto.ChatResponse
import com.ai.assignment.thread.domain.repository.ThreadRepository
import com.ai.assignment.user.domain.repository.UserRepository
import com.ai.assignment.chat.domain.Chat
import com.ai.assignment.thread.domain.Thread
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class CommandChatService (
    private val chatRepository: ChatRepository,
    private val threadRepository: ThreadRepository,
    private val userRepository: UserRepository,
    private val openAiService: OpenAiService,
    private val authReader: AuthReader
) {

    fun createChat(request: ChatCreateRequest): ChatResponse {
        val user = authReader.getCurrentUser()

        val thread = getOrCreateThread(user.id!!)

        val existingChats = chatRepository.findByThreadId(thread.id!!)
        val messages = mutableListOf<String>()

        existingChats.forEach { chat ->
            messages.add("User: ${chat.question}")
            messages.add("Assistant: ${chat.answer}")
        }
        messages.add("User: ${request.question}")

        val answer = openAiService.generateAnswer(messages, request.model)

        val chat = Chat(
            thread = thread,
            question = request.question,
            answer = answer
        )
        val savedChat = chatRepository.save(chat)

        return ChatResponse(
            id = savedChat.id!!,
            question = savedChat.question,
            answer = savedChat.answer,
            createdAt = savedChat.createdAt
        )
    }

    fun deleteThread(threadId: Long) {
        val thread = threadRepository.findById(threadId).orElseThrow()

        if (thread.user.id != authReader.getCurrentUser().id) {
            throw RuntimeException("권한이 없습니다")
        }

        threadRepository.delete(thread)
    }

    private fun getOrCreateThread(userId: Long): Thread {
        val thirtyMinutesAgo = LocalDateTime.now().minusMinutes(30)

        val recentThreads = threadRepository.findByUserIdAndCreatedAtAfterOrderByCreatedAtDesc(
            userId, thirtyMinutesAgo
        )

        return if (recentThreads.isNotEmpty()) {
            recentThreads.first()
        } else {
            val user = userRepository.findById(userId).orElseThrow()
            val newThread = Thread(user = user)
            threadRepository.save(newThread)
        }
    }
}