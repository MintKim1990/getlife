package com.project.getlife.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import reactor.core.publisher.Mono

@RestControllerAdvice
class ExceptionAdvice {

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): Mono<ResponseEntity<ErrorResponse>> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = exception.message ?: "서버에러가 발생했습니다",
            errorCode = "ERROR_O01",
        )
        return Mono.just(ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR))
    }

}