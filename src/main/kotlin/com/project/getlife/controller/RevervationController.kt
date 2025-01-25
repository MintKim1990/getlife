package com.project.getlife.controller

import com.project.getlife.service.ReservationService
import com.project.getlife.service.request.ReservationSaveRequest
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/reservation")
class RevervationController(
    private val reservationService: ReservationService
) {

    @Schema(description = "목표 저장")
    @PostMapping("/save")
    suspend fun save(@RequestBody reservationSaveRequest: ReservationSaveRequest): Mono<Void> {
        return reservationService.save(reservationSaveRequest)
    }

}