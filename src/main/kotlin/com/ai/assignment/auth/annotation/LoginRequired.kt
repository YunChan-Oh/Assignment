package com.ai.assignment.auth.annotation

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@LoginOrNot
annotation class LoginRequired