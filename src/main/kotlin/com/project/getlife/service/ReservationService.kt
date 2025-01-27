package com.project.getlife.service

import com.project.getlife.common.getLogger
import com.project.getlife.domain.*
import com.project.getlife.service.request.ReservationLogSaveRequest
import com.project.getlife.service.request.ReservationResponse
import com.project.getlife.service.request.ReservationSaveRequest
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
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
    private val reservationLogsRepository: ReservationLogsRepository,
) {

    private val logger = getLogger()

    /**
     * 목표 조회
     */
    suspend fun findReservation(): Flux<ReservationResponse> {
        return reservationRepository.findAll()
            .flatMap { reservation ->
                recurringRulesRepository.findByReservationId(reservation.id!!)
                    .collectList() // Flux<RecurringRules>를 Mono<List<RecurringRules>>로 변환
                    .map { recurringRulesList ->
                        ReservationResponse(
                            title = reservation.title,
                            weekDays = recurringRulesList.map { it.weekday }.toSet(),
                            startDate = reservation.startDate,
                            endDate = reservation.endDate
                        )
                    }
            }
    }

    /**
     * 목표 저장
     */
    suspend fun saveReservation(reservationSaveRequest: ReservationSaveRequest): Mono<Void> {
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

    /**
     * 목표 이력 저장
     */
    suspend fun saveReservationLog(reservationId: Long, reservationLogSaveRequest: ReservationLogSaveRequest) {
        reservationLogsRepository.findByPerformedDate(reservationLogSaveRequest.performedDate).awaitSingleOrNull()
            ?.let {
                throw Exception("이미 등록된 결과가 존재합니다.")
            }
        reservationLogsRepository.save(reservationLogSaveRequest.toReservationLogs(reservationId)).awaitSingle()
    }


}