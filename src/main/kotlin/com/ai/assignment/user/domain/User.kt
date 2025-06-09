package com.ai.assignment.user.domain

import com.ai.assignment.common.domain.BaseEntity
import com.ai.assignment.user.domain.value.Role
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(

    @Column(nullable = false, unique = true, length = 100)
    val email: String,

    @Column(nullable = false, length = 255)
    val password: String,

    @Column(nullable = false, length = 50)
    val name: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    val role: Role

) : BaseEntity() {
}
