package com.devpulse.status

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/status")
class StatusController(private val statusService: StatusService) {

    @PostMapping
    fun setStatus(
        @RequestHeader("X-Username") username: String,
        @RequestBody request: StatusRequest
    ): ResponseEntity<StatusResponse> =
        ResponseEntity.ok(statusService.setStatus(username, request))

    @GetMapping("/feed")
    fun getFeed(): ResponseEntity<List<StatusFeedItem>> =
        ResponseEntity.ok(statusService.getFeed())
}
