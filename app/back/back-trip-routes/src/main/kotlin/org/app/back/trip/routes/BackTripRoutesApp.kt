package org.app.back.trip.routes

import org.app.back.trip.routes.config.BackTripRoutesProperties
import org.app.back.trip.routes.service.RoutesService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.Async

@Async
@EnableJpaRepositories(
    basePackages = ["org.app.back.trip.routes.repository"]
)
@ComponentScan(
    basePackages = ["org.app.back.trip.routes.*"]
)
@EnableConfigurationProperties(BackTripRoutesProperties::class)
@SpringBootApplication
class BackTripRoutesApp

fun main(args: Array<String>) {
    val context = runApplication<BackTripRoutesApp>(*args)

    val routesService = context.getBean(RoutesService::class.java)

    routesService.save()
}