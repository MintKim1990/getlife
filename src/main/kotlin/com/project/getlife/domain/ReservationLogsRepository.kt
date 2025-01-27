package com.project.getlife.domain

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.time.LocalDate

@Repository
interface ReservationLogsRepository : ReactiveCrudRepository<ReservationLogs, Long> {

//    @Query(
//        """
//            select id
//                 , reservationId
//                 , performedDate
//                 , notes
//                 , createdAt
//              from ReservationLogs
//             where performedDate = :performedDate
//        """
//    )
    fun findByPerformedDate(performedDate: LocalDate): Mono<ReservationLogs?>

}