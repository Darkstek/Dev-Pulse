package com.devpulse.user

import java.time.LocalDateTime

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class AuthResponse(
    val token: String,
    val userId: Long,
    val username: String
)

data class UserResponse(
    val id: Long,
    val username: String,
    val email: String,
    val createdAt: LocalDateTime
)

fun User.toResponse() = UserResponse(id, username, email, createdAt)
