package com.ai.assignment.auth.interceptor

import com.ai.assignment.auth.annotation.AdminOnly
import com.ai.assignment.auth.annotation.LoginOrNot
import com.ai.assignment.auth.annotation.LoginRequired
import com.ai.assignment.auth.service.implementation.AuthReader
import com.ai.assignment.auth.service.implementation.AuthUpdater
import com.ai.assignment.auth.util.BearerTokenExtractor
import com.ai.assignment.auth.util.JwtParser
import com.ai.assignment.user.domain.User
import com.ai.assignment.user.domain.value.Role
import com.ai.assignment.user.service.implementation.UserReader
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@Configuration
class AuthInterceptor(
    private val jwtParser: JwtParser,
    private val authUpdater: AuthUpdater,
    private val authReader: AuthReader,
    private val userReader: UserReader
) : HandlerInterceptor {

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        if (handler is HandlerMethod) {
            if (handler.hasMethodAnnotation(LoginOrNot::class.java)) {
                val bearer = request.getHeader(AUTHORIZATION)
                if (bearer == null) {
                    authUpdater.updateCurrentUser(null)
                } else {
                    val jwt = BearerTokenExtractor.extract(bearer)
                    val userId = jwtParser.getIdFromJwt(jwt)
                    val user = userReader.read(userId)
                    authUpdater.updateCurrentUser(user)
                }
            }

            if (handler.hasMethodAnnotation(LoginRequired::class.java)) {
                if (authReader.getNullableCurrentUser() == null) {
                    throw RuntimeException("토큰이 존재하지 않습니다.")
                }
            }

            if (handler.hasMethodAnnotation(AdminOnly::class.java)) {
                val currentUser = authReader.getCurrentUser()
                shouldUserAdmin(currentUser)
            }
        }
        return true
    }

    private fun shouldUserAdmin(currentUser: User) {
        if (currentUser.role != Role.ADMIN) {
            throw RuntimeException("어드민 권한이 존재하지 않습니다.")
        }
    }
}