package com.project.getlife.service.request

import com.project.getlife.domain.Reservation
import com.project.getlife.enum.Weekday
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "목표세우기 요청 클래스")
data class ReservationSaveRequest(

    @Schema(
        description = "목표",
        example = "Weekly Meeting",
        required = true
    )
    val title: String,

    @Schema(
        description = "반복 요일",
        example = "[\"MON\", \"TUE\", \"WED\", \"THU\", \"FRI\", \"SAT\", \"SUN\"]",
        required = true
    )
    val weekDays: Set<Weekday>,

    @Schema(
        description = "기간 시작일",
        example = "2024-01-01",
        required = true
    )
    val startDate: LocalDate,

    @Schema(
        description = "기간 종료일",
        example = "2024-01-01",
        required = true
    )
    val endDate: LocalDate
) {

    fun toReservation(): Reservation {
        return Reservation(
            title = this.title!!,
            startDate = this.startDate!!,
            endDate = this.endDate!!
        )
    }

}
