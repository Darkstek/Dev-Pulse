package com.devpulse.status

import jakarta.persistence.*
import java.time.LocalDateTime

enum class Status {
    CODING, DEBUGGING, COFFEE, CRYING, DEPLOYING
}

@Entity
@Table(name = "status_events")
class StatusEvent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val userId: Long,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: Status,

    @Column(nullable = false)
    val timestamp: LocalDateTime = LocalDateTime.now()
)
