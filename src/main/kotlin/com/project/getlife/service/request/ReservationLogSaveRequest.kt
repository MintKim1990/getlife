package com.project.getlife.service.request

import com.project.getlife.domain.ReservationLogs
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "목표세우기 요청 클래스")
data class ReservationLogSaveRequest(

    @Schema(
        description = "목표 수행일자",
        example = "2024-01-01",
        required = true
    )
    val performedDate: LocalDate,

    @Schema(
        description = "목표 수행메모",
        required = false
    )
    val notes: String? = null,

) {

    fun toReservationLogs(reservationId: Long): ReservationLogs {
        return ReservationLogs(
            reservationId = reservationId,
            performedDate = performedDate,
            notes = notes
        )
    }

}
