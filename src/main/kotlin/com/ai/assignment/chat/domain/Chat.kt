package com.ai.assignment.chat.domain

import com.ai.assignment.common.domain.BaseEntity
import com.ai.assignment.thread.domain.Thread
import jakarta.persistence.*

@Entity
@Table(name = "chats")
class Chat(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thread_id", nullable = false)
    val thread: Thread,

    @Lob
    @Column(nullable = false)
    val question: String,

    @Lob
    @Column(nullable = false)
    val answer: String

) : BaseEntity() {
}
