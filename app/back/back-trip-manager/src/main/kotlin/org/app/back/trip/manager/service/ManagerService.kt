package org.app.back.trip.manager.service

import org.app.back.trip.manager.dto.RoutesInfoRq
import org.app.back.trip.manager.dto.RoutesInfoRs


interface ManagerService {
    fun routesInfo(routesInfoRq: RoutesInfoRq): RoutesInfoRs
}