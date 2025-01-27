package com.project.getlife.domain

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface RecurringRulesRepository : ReactiveCrudRepository<RecurringRules, Long> {

    fun findByReservationId(reservationId: Long): Flux<RecurringRules>

}