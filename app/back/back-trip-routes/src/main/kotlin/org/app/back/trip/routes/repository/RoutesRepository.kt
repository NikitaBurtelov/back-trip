package org.app.back.trip.routes.repository

import org.app.back.trip.routes.db.domain.RoutesEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoutesRepository: JpaRepository<RoutesEntity, Int> {
}