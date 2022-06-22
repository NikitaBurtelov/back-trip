package org.app.back.trip.manager

import io.github.cdimascio.dotenv.dotenv
import org.app.back.trip.manager.config.BackTripManagerProperties
import org.app.back.trip.manager.dto.RoutesInfoRq
import org.app.back.trip.manager.service.ManagerService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.Async
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import java.util.*

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

    val context = runApplication<BackTripManagerApplication>(*args)
    val managerServiceBean = context.getBean(ManagerService::class.java)

    val routesInfoRq = RoutesInfoRq(
        "Коломна",
        "Отдых",
    )

    managerServiceBean.routesInfo(routesInfoRq)


}