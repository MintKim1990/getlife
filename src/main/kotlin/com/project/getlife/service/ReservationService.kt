package com.project.getlife.service

import com.project.getlife.domain.RecurringRules
import com.project.getlife.domain.RecurringRulesRepository
import com.project.getlife.domain.ReservationRepository
import com.project.getlife.service.request.ReservationSaveRequest
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.mono
import org.springframework.stereotype.Service
import org.springframework.transaction.reactive.TransactionalOperator
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ReservationService(
    private val transactionalOperator: TransactionalOperator,
    private val recurringRulesRepository: RecurringRulesRepository,
    private val reservationRepository: ReservationRepository,
) {

    suspend fun save(reservationSaveRequest: ReservationSaveRequest): Mono<Void> {
        return transactionalOperator.execute {
            mono {
                reservationRepository.save(reservationSaveRequest.toReservation())
                    .awaitSingle()
                    .let { reservation ->
                        reservationSaveRequest.weekDays.forEach {
                            recurringRulesRepository.save(
                                RecurringRules(
                                    reservationId = reservation.id!!,
                                    weekday = it
                                )
                            ).awaitSingle()
                        }
                    }
            }
        }.then()
    }


}