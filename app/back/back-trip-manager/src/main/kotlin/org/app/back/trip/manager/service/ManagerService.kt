package org.app.back.trip.manager.service

import org.app.back.trip.manager.dto.RouteSearchRq
import org.app.back.trip.manager.dto.RouteSearchRs
import org.app.back.trip.manager.dto.RoutesInfoRq
import org.app.back.trip.manager.dto.RoutesInfoRs


interface ManagerService {
    fun stationInfo(request: RouteSearchRq): RouteSearchRs
    fun routesInfo(routesInfoRq: RoutesInfoRq): RoutesInfoRs
}