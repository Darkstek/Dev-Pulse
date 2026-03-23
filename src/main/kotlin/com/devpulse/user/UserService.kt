package com.devpulse.user

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.security.MessageDigest
import java.util.UUID

@Service
class UserService(private val userRepository: UserRepository) {

    @Transactional
    fun register(request: RegisterRequest): UserResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalArgumentException("Email already in use")
        }
        if (userRepository.existsByUsername(request.username)) {
            throw IllegalArgumentException("Username already taken")
        }

        val user = User(
            username = request.username,
            email = request.email,
            passwordHash = hashPassword(request.password)
        )

        return userRepository.save(user).toResponse()
    }

    fun login(request: LoginRequest): AuthResponse {
        val user = userRepository.findByEmail(request.email)
            ?: throw IllegalArgumentException("Invalid email or password")

        if (user.passwordHash != hashPassword(request.password)) {
            throw IllegalArgumentException("Invalid email or password")
        }

        // TODO: replace with real JWT generation (e.g. jjwt or spring-security-oauth2)
        val token = "placeholder.jwt.${UUID.randomUUID()}"

        return AuthResponse(token = token, userId = user.id, username = user.username)
    }

    private fun hashPassword(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        return digest.digest(password.toByteArray())
            .joinToString("") { "%02x".format(it) }
    }
}
