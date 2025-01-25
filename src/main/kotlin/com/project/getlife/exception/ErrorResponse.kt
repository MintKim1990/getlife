package com.project.getlife.exception

data class ErrorResponse(
    val status: Int,
    val message: String,
    val errorCode: String
)