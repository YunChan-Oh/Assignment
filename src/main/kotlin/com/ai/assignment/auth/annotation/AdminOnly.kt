package com.ai.assignment.auth.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@LoginRequired
annotation class AdminOnly