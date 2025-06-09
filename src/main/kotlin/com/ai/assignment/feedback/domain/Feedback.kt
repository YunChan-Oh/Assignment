package com.ai.assignment.feedback.domain

import com.ai.assignment.chat.domain.Chat
import com.ai.assignment.common.domain.BaseEntity
import com.ai.assignment.feedback.domain.value.FeedbackStatus
import com.ai.assignment.user.domain.User
import jakarta.persistence.*

@Entity
@Table(
    name = "feedbacks",
    uniqueConstraints = [UniqueConstraint(columnNames = ["user_id", "chat_id"])]
)
class Feedback(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", nullable = false)
    val chat: Chat,

    @Column(name = "is_positive", nullable = false)
    val isPositive: Boolean,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    val status: FeedbackStatus

) : BaseEntity() {
}
