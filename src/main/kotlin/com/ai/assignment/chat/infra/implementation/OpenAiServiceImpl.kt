package com.ai.assignment.chat.infra.implementation

import com.ai.assignment.chat.infra.OpenAiService
import org.springframework.stereotype.Service

@Service
class OpenAiServiceImpl : OpenAiService {

    override fun generateAnswer(messages: List<String>, model: String?): String {
        // 추후 AI 기능을 실제로 사용할 때 추가 구현해야 합니다.
        return "이것은 AI가 생성한 답변입니다. 질문: ${messages.last()}"
    }
}