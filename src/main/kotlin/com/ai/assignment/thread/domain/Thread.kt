package com.ai.assignment.thread.domain

import com.ai.assignment.common.domain.BaseEntity
import com.ai.assignment.user.domain.User
import jakarta.persistence.*

@Entity
@Table(name = "threads")
class Thread(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User

) : BaseEntity() {
}
