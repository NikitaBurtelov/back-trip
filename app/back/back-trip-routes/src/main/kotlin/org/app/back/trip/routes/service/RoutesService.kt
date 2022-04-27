package org.app.back.trip.routes.service

import org.app.back.trip.routes.dto.response.StationsRs

interface RoutesService {
    fun station(): StationsRs
    fun save()
}