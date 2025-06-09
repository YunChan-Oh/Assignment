package com.ai.assignment.auth.util


object BearerTokenExtractor {

    private const val BEARER_TYPE = "Bearer "
    private const val BEARER_JWT_REGEX = "^Bearer [A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$"

    fun extract(bearer: String?): String {
        validate(bearer)
        return bearer!!.replace(BEARER_TYPE, "").trim()
    }

    private fun validate(authorization: String?) {
        if (authorization == null) {
            throw RuntimeException("토큰이 존재하지 않습니다.")
        }
        if (!authorization.matches(BEARER_JWT_REGEX.toRegex())) {
            throw RuntimeException("토큰이 유효하지 않습니다.")
        }
    }
}