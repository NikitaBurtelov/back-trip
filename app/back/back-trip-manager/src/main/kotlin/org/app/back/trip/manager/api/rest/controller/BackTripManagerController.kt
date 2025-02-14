package org.app.back.trip.manager.api.rest.controller

import mu.KotlinLogging
import org.app.back.trip.manager.dto.RoutesInfoRq
import org.app.back.trip.manager.dto.RoutesInfoRs
import org.app.back.trip.manager.service.ManagerService
import org.app.back.trip.manager.service.RoutesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class BackTripManagerController @Autowired constructor(
    val managerService: ManagerService,
    val routesService: RoutesService
) {
    val log = KotlinLogging.logger {}

    @PostMapping("/info")
    fun timetable(
        @RequestBody routesInfoRq: RoutesInfoRq
    ): ResponseEntity<RoutesInfoRs> {
        log.info("Request received: $routesInfoRq")
        val routeSearchRq = managerService.stationInfo(routesInfoRq)
        val routesInfoRs = routesService.searchRoutes(routeSearchRq)
        return ResponseEntity.ok(
            routesInfoRs
        )
    }
}