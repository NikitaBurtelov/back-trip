package org.app.back.trip.manager.api.rest.controller

import org.app.back.trip.manager.dto.RoutesInfoRq
import org.app.back.trip.manager.dto.RoutesInfoRs
import org.app.back.trip.manager.service.ManagerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1")
class BackTripManagerController @Autowired constructor(
    val managerService: ManagerService
) {

    @GetMapping("/info")
    fun tripsDiscovery(
        @RequestHeader("taskId") taskId: UUID,
        @RequestBody routesInfoRq: RoutesInfoRq
    ): ResponseEntity<RoutesInfoRs> {
        return try {
          ResponseEntity.ok(
              managerService.routesInfo(routesInfoRq)
          )
        }
        catch (e: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}