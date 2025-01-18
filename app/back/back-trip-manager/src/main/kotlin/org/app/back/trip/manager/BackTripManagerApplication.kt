package org.app.back.trip.manager

import io.github.cdimascio.dotenv.dotenv
import org.app.back.trip.manager.config.properties.BackTripManagerProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableConfigurationProperties(BackTripManagerProperties::class)
@EnableWebMvc
@ComponentScan(
    basePackages = ["org.app.back.trip.manager.*"]
)
@EnableJpaRepositories(
    basePackages = ["org.app.back.trip.manager.repository"]
)
@SpringBootApplication
class BackTripManagerApplication

fun main(args: Array<String>) {
    dotenv {
        filename = ".env"
        ignoreIfMalformed = true
        ignoreIfMissing = true
        systemProperties = true
    }
    runApplication<BackTripManagerApplication>(*args)
}