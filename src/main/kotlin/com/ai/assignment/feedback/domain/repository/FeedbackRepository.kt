package com.ai.assignment.feedback.domain.repository

import com.ai.assignment.feedback.domain.Feedback
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FeedbackRepository : JpaRepository<Feedback, Long> {
}