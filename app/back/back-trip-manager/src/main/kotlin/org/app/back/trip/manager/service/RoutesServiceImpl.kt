package org.app.back.trip.manager.service

import mu.KotlinLogging
import org.app.back.trip.manager.dto.RouteSearchRs
import org.app.back.trip.manager.dto.Routes
import org.app.back.trip.manager.dto.RoutesInfoRs
import org.springframework.stereotype.Service

@Service
class RoutesServiceImpl: RoutesService {
    val log = KotlinLogging.logger {  }

    override fun searchRoutes(routeSearchRs: RouteSearchRs): RoutesInfoRs {
        log.info("The start of the formation of a RoutesInfoRs type message")
        val routesInfoRs = mappingRoutes(routeSearchRs)
        log.info(
            "The end of the formation of a RoutesInfoRs type message" +
            "message: $routesInfoRs"
        )

        return routesInfoRs
    }

    private fun mappingRoutes(routeSearchRs: RouteSearchRs) : RoutesInfoRs {
        val firstRoute = routeSearchRs.segments.first()
        return RoutesInfoRs(
            to = firstRoute.to!!.title,
            from = firstRoute.from!!.title,
            routes = routeSearchRs.segments.map {
                Routes(
                    transportType = it.thread!!.transportType,
                    departure = it.departure!!,
                    arrival = it.arrival!!
                )

            }
        )
    }
}