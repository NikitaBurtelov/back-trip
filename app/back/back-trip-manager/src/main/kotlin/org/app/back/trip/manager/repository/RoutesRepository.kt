package org.app.back.trip.manager.repository

import org.app.back.trip.manager.db.domain.RoutesEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoutesRepository: JpaRepository<RoutesEntity, Int> {
    fun findFirstByTitleAndStationTypeAllIgnoreCase(
        title: String,
        stationType: String? = "platform"
    ): RoutesEntity

    fun findFirstByTitleAndStationTypeInIgnoreCase(
        title: String,
        stationType: Collection<String>
    ): RoutesEntity

    fun findByTitleAndStationTypeAllIgnoreCase(
        title: String,
        stationType: String? = "platform"
    ): RoutesEntity

    fun findByTitleAndStationTypeInIgnoreCase(
        title: String,
        stationType: Collection<String>
    ): RoutesEntity
}