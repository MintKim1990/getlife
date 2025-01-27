package com.project.getlife.service.request

import com.project.getlife.domain.RecurringRules
import com.project.getlife.domain.Reservation
import com.project.getlife.enum.Weekday
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class ReservationResponse(
    @Schema(description = "목표") val title: String,
    @Schema(description = "반복 요일") val weekDays: Set<Weekday>,
    @Schema(description = "기간 시작일") val startDate: LocalDate,
    @Schema(description = "기간 종료일") val endDate: LocalDate
) {
    fun toResponse(reservation: Reservation, reservationRules: List<RecurringRules>): ReservationResponse {
        return ReservationResponse(
            title = reservation.title,
            weekDays = reservationRules.map { it.weekday }.toSet(),
            startDate = reservation.startDate,
            endDate = reservation.endDate
        )
    }
}
