package org.app.back.trip.routes.repository

import org.app.back.trip.routes.db.domain.RoutesEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RoutesRepository: JpaRepository<RoutesEntity, Long> {
}