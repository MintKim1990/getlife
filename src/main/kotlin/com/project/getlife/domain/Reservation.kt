package com.project.getlife.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("Reservation")
data class Reservation(
    @Id
    val id: Long? = null,

    @Column("title")
    val title: String,

    @Column("start_date")
    val startDate: LocalDate,

    @Column("end_date")
    val endDate: LocalDate
) {

}