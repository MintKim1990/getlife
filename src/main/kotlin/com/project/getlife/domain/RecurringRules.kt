package com.project.getlife.domain

import com.project.getlife.enum.Weekday
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("RecurringRules")
data class RecurringRules(
    @Id
    val id: Long? = null,

    @Column("reservation_id")
    val reservationId: Long,

    @Column("weekday")
    val weekday: Weekday
)