package com.project.getlife.controller

import com.project.getlife.service.ReservationService
import com.project.getlife.service.request.ReservationLogSaveRequest
import com.project.getlife.service.request.ReservationResponse
import com.project.getlife.service.request.ReservationSaveRequest
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/reservation")
class ReservationController(
    private val reservationService: ReservationService
) {

    @Schema(description = "목표 조회")
    @GetMapping
    suspend fun saveReservation(): Flux<ReservationResponse> {
        return reservationService.findReservation()
    }

    @Schema(description = "목표 저장")
    @PostMapping
    suspend fun saveReservation(@RequestBody reservationSaveRequest: ReservationSaveRequest): Mono<Void> {
        return reservationService.saveReservation(reservationSaveRequest)
    }

    @Schema(description = "목표 이력 저장")
    @PostMapping("/log/{reservationId}")
    suspend fun saveReservationLog(
        @PathVariable reservationId: Long,
        @RequestBody reservationLogSaveRequest: ReservationLogSaveRequest
    ) {
        reservationService.saveReservationLog(reservationId = reservationId, reservationLogSaveRequest = reservationLogSaveRequest)
    }

}