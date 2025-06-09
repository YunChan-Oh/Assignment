package com.ai.assignment.chat.service

import com.ai.assignment.auth.service.implementation.AuthReader
import com.ai.assignment.chat.domain.repository.ChatRepository
import com.ai.assignment.chat.presentation.dto.ChatListResponse
import com.ai.assignment.chat.presentation.dto.ChatResponse
import com.ai.assignment.chat.presentation.dto.ThreadResponse
import com.ai.assignment.user.domain.value.Role
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryChatService (
    private val chatRepository: ChatRepository,
    private val authReader: AuthReader
) {

    fun getChatList(
        page: Int,
        size: Int,
        sortDirection: String
    ): ChatListResponse {
        val user = authReader.getCurrentUser()
        val pageable = PageRequest.of(page, size)

        val chatPage = if (user.role == Role.ADMIN) {
            chatRepository.findAll(pageable)
        } else {
            chatRepository.findByThreadUserId(user.id!!, pageable)
        }

        val threadGroups = chatPage.content.groupBy { it.thread.id }

        val threadResponses = threadGroups.map { (threadId, chats) ->
            val thread = chats.first().thread
            val chatResponses = chats.map { chat ->
                ChatResponse(
                    id = chat.id!!,
                    question = chat.question,
                    answer = chat.answer,
                    createdAt = chat.createdAt
                )
            }

            ThreadResponse(
                threadId = threadId!!,
                createdAt = thread.createdAt,
                chats = chatResponses
            )
        }

        return ChatListResponse(
            content = threadResponses,
            totalElements = chatPage.totalElements,
            totalPages = chatPage.totalPages,
            number = chatPage.number,
            size = chatPage.size
        )
    }
}