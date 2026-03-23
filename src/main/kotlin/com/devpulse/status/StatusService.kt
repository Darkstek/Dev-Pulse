package com.devpulse.status

import org.springframework.data.domain.PageRequest
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

data class StatusRequest(val status: String)

data class StatusResponse(
    val message: String,
    val userId: Long,
    val status: Status,
    val timestamp: LocalDateTime
)

data class StatusFeedItem(
    val userId: Long,
    val status: Status,
    val timestamp: LocalDateTime
)

private val funnyMessages = mapOf(
    Status.CODING to listOf(
        "Keyboard goes brrr.",
        "In the zone. Do not disturb. Seriously.",
        "10x developer mode: activated."
    ),
    Status.DEBUGGING to listOf(
        "It works on my machine. Shipping the machine.",
        "The bug is always in the last place you look. Obviously.",
        "Have you tried adding more print statements?"
    ),
    Status.COFFEE to listOf(
        "Refueling. Back in 2-5 business coffees.",
        "The real MVP of this sprint.",
        "Blood type: cold brew."
    ),
    Status.CRYING to listOf(
        "The compiler has no idea what it's talking about.",
        "Stack Overflow has failed me. I am alone.",
        "It was working yesterday. I changed nothing. I hate everything."
    ),
    Status.DEPLOYING to listOf(
        "YOLO push to prod. What could go wrong?",
        "Praying to the CI/CD gods. Light a candle.",
        "Friday 4:58 PM deploy. Living on the edge."
    )
)

@Service
class StatusService(
    private val statusRepository: StatusRepository,
    private val messagingTemplate: SimpMessagingTemplate
) {

    @Transactional
    fun setStatus(userId: Long, request: StatusRequest): StatusResponse {
        val status = try {
            Status.valueOf(request.status.uppercase())
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Invalid status '${request.status}'. Valid values: ${Status.entries.joinToString()}")
        }

        val event = statusRepository.save(
            StatusEvent(userId = userId, status = status)
        )

        val message = funnyMessages[status]!!.random()
        val response = StatusResponse(message = message, userId = event.userId, status = event.status, timestamp = event.timestamp)
        messagingTemplate.convertAndSend("/topic/feed", response)
        return response
    }

    fun getFeed(): List<StatusFeedItem> =
        statusRepository.findAllByOrderByTimestampDesc(PageRequest.of(0, 20))
            .map { StatusFeedItem(userId = it.userId, status = it.status, timestamp = it.timestamp) }
}
