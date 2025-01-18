package org.app.back.trip.manager.service

import org.app.back.trip.manager.dto.RouteSearchRs
import org.app.back.trip.manager.dto.RoutesInfoRs

interface RoutesService {
    fun searchRoutes(routeSearchRs: RouteSearchRs): RoutesInfoRs
}