package com.ai.assignment.common.config

import com.ai.assignment.auth.interceptor.AuthInterceptor
import com.ai.assignment.auth.service.implementation.AuthReader
import com.ai.assignment.auth.service.implementation.AuthUpdater
import com.ai.assignment.auth.util.JwtParser
import com.ai.assignment.user.service.implementation.UserReader
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders.LOCATION
import org.springframework.http.HttpHeaders.SET_COOKIE
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class InterceptorConfig(
    private val jwtParser: JwtParser,
    private val authUpdater: AuthUpdater,
    private val authReader: AuthReader,
    private val userReader: UserReader
) : WebMvcConfigurer {

    companion object {
        private const val ALLOW_ALL_PATH = "/**"
        private const val ALLOWED_METHODS = "*"
        private const val MAIN_SERVER_DOMAIN = "https://assignment.com"
        private const val FRONTEND_LOCALHOST = "http://localhost:3000"
        private const val HTTPS_FRONTEND_LOCALHOST = "https://localhost:3000"
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping(ALLOW_ALL_PATH)
            .allowedMethods(ALLOWED_METHODS)
            .allowedOrigins(
                MAIN_SERVER_DOMAIN,
                FRONTEND_LOCALHOST,
                HTTPS_FRONTEND_LOCALHOST
            )
            .allowCredentials(true)
            .exposedHeaders(LOCATION, SET_COOKIE)
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(
            AuthInterceptor(
                jwtParser = jwtParser,
                authUpdater = authUpdater,
                authReader = authReader,
                userReader = userReader
            )
        )
    }
}
