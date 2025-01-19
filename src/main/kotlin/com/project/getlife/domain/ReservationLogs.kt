package com.project.getlife.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.time.LocalDateTime

@Table("ReservationLogs")
data class ReservationLogs (
    @Id
    val id: Long? = null,

    @Column("reservation_id")
    val reservationId: Long,

    @Column("performed_date")
    val performedDate: LocalDate,

    @Column("notes")
    val notes: String? = null,

    @Column("created_at")
    val createdAt: LocalDateTime? = null
) {

}