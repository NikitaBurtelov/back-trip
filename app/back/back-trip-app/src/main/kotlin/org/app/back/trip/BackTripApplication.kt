package org.app.back.trip

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.scheduling.annotation.Async

@Async
@ComponentScan(
    basePackages = ["org.app.back.trip.*"]
)
@SpringBootApplication
class BackTripApplication {
    fun main(args: Array<String>) {
        runApplication<BackTripApplication>(*args)
    }
}