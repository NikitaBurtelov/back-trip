package org.app.back.trip.manager.service

import org.app.back.trip.manager.dto.RouteSearchRs
import org.app.back.trip.manager.dto.RoutesInfoRq


interface ManagerService {
    fun stationInfo(request: RoutesInfoRq): RouteSearchRs
}