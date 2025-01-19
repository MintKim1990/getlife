package com.project.getlife.domain

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ReservationLogsRepository : ReactiveCrudRepository<ReservationLogs, Long> {
}