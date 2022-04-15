package org.app.back.trip.manager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.scheduling.annotation.Async

@Async
@ComponentScan(
    basePackages = ["org.app.back.trip.manager.*"]
)
@SpringBootApplication
class BackTripManagerApplication {
    fun main(args: Array<String>) {
        runApplication<BackTripManagerApplication>(*args)
    }
}