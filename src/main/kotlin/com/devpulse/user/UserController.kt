package com.devpulse.user

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class UserController(private val userService: UserService) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<UserResponse> {
        val user = userService.register(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(user)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<AuthResponse> {
        val auth = userService.login(request)
        return ResponseEntity.ok(auth)
    }
}

@RestControllerAdvice
class UserExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(ex: IllegalArgumentException): ResponseEntity<Map<String, String>> =
        ResponseEntity.badRequest().body(mapOf("error" to (ex.message ?: "Bad request")))
}
