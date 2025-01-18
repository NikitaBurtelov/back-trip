package org.app.back.trip

import io.github.cdimascio.dotenv.dotenv
import org.app.back.trip.config.properties.BackTripAppProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.scheduling.annotation.Async

@EnableConfigurationProperties(BackTripAppProperties::class)
@Async
@ComponentScan(
    basePackages = ["org.app.back.trip.*"]
)
@SpringBootApplication
class BackTripApplication

fun main(args: Array<String>) {
    dotenv {
        filename = ".env"
        ignoreIfMalformed = true
        ignoreIfMissing = true
        systemProperties = true
    }
    runApplication<BackTripApplication>(*args)
}