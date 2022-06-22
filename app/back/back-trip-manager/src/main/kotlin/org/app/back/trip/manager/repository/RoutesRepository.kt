package org.app.back.trip.manager.repository

import org.app.back.trip.manager.db.domain.RoutesEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoutesRepository: JpaRepository<RoutesEntity, Int> {
    fun findByTitleAndStationTypeAllIgnoreCase(title: String, stationType: String? = "platform"): RoutesEntity
}