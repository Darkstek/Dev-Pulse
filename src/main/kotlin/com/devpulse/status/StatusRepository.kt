package com.devpulse.status

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.domain.Pageable

interface StatusRepository : JpaRepository<StatusEvent, Long> {
    fun findAllByOrderByTimestampDesc(pageable: Pageable): List<StatusEvent>
}
