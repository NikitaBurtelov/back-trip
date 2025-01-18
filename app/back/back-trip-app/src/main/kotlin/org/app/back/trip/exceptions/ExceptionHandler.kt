package org.app.back.trip.exceptions

import mu.KotlinLogging
import org.apache.http.NoHttpResponseException
import org.apache.http.conn.HttpHostConnectException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.net.SocketTimeoutException

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ExceptionHandler {
    val log = KotlinLogging.logger {  }

    @ExceptionHandler(NoHttpResponseException::class)
    fun handleNoHttpResponseException(e: NoHttpResponseException): ResponseEntity<String> {
        val message = "NoHttpResponseException: " + e.message
        log.warn { message }
        return ResponseEntity.badRequest().body(message)
    }

    @ExceptionHandler(SocketTimeoutException::class)
    fun handleSocketTimeoutException(e: SocketTimeoutException): ResponseEntity<String> {
        val message = "SocketTimeoutException: " + e.message
        log.warn { message }
        return ResponseEntity.badRequest().body(message)
    }

    @ExceptionHandler(HttpHostConnectException::class)
    fun handleHttpHostConnectException(e: HttpHostConnectException): ResponseEntity<String> {
        val message = "HttpHostConnectException: " + e.message
        log.warn { message }
        return ResponseEntity.badRequest().body(message)
    }
}