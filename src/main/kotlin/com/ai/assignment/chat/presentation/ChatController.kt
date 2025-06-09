package com.ai.assignment.chat.presentation

import com.ai.assignment.chat.presentation.dto.ChatCreateRequest
import com.ai.assignment.chat.presentation.dto.ChatListResponse
import com.ai.assignment.chat.presentation.dto.ChatResponse
import com.ai.assignment.chat.service.CommandChatService
import com.ai.assignment.chat.service.QueryChatService
import com.ai.assignment.user.domain.value.Role
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/chats")
class ChatController(
    private val commandChatService: CommandChatService,
    private val queryChatService: QueryChatService
) {

    @PostMapping
    fun createChat(
        @RequestBody request: ChatCreateRequest
    ): ResponseEntity<ChatResponse> {
        val response = commandChatService.createChat(request)
        return ResponseEntity.ok(response)
    }

    @GetMapping
    fun getChatList(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam(defaultValue = "DESC") sortDirection: String
    ): ResponseEntity<ChatListResponse> {
        val response = queryChatService.getChatList(page, size, sortDirection)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/threads/{threadId}")
    fun deleteThread(
        @PathVariable threadId: Long
    ): ResponseEntity<Void> {
        commandChatService.deleteThread(threadId)
        return ResponseEntity.noContent().build()
    }
}