package org.app.back.trip.routes.service

import org.app.back.trip.routes.db.domain.RoutesEntity
import java.net.URL

interface RoutesService {
    fun parse(pageUrl: URL): RoutesEntity
    fun save(routesEntity: RoutesEntity)
}