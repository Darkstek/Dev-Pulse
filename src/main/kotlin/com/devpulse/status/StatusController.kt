package com.devpulse.status

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/status")
class StatusController(private val statusService: StatusService) {

    @PostMapping
    fun setStatus(
        @RequestHeader("userId") userId: Long,
        @RequestBody request: StatusRequest
    ): ResponseEntity<StatusResponse> =
        ResponseEntity.ok(statusService.setStatus(userId, request))

    @GetMapping("/feed")
    fun getFeed(): ResponseEntity<List<StatusFeedItem>> =
        ResponseEntity.ok(statusService.getFeed())
}
