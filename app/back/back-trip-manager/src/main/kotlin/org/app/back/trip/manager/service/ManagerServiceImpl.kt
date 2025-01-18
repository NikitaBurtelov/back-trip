package org.app.back.trip.manager.service

import com.google.gson.GsonBuilder
import mu.KotlinLogging
import org.app.back.trip.manager.config.properties.BackTripManagerProperties
import org.app.back.trip.manager.db.domain.RoutesEntity
import org.app.back.trip.manager.dto.RouteSearchRs
import org.app.back.trip.manager.dto.RoutesInfoRq
import org.app.back.trip.manager.repository.RoutesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ManagerServiceImpl @Autowired constructor(
    private val restTemplate: RestTemplate,
    private val properties: BackTripManagerProperties,
    private val routesRepository: RoutesRepository
): ManagerService {
    val log = KotlinLogging.logger {}

    override fun stationInfo(request: RoutesInfoRq): RouteSearchRs {
        try {
            val codeStationFrom = findRoutesEntityByTitle(request.from).codeStation
            val codeStationTo = findRoutesEntityByTitle(request.to).codeStation

            val resp = restTemplate.getForEntity(
                properties.baseUrl +
                        "/${properties.version}" +
                        "/search/?" +
                        "apikey=${properties.key}" +
                        "&format=${properties.format}" +
                        "&from=$codeStationFrom" +
                        "&to=$codeStationTo" +
                        "&date=${request.date}" +
                        "&transfers=false" +
                        "&lang=${properties.lang}",
                String::class.java
            )

            log.info("YANDEX rs: ${resp.body}")

            val gson = GsonBuilder().setPrettyPrinting().create()

            return gson.fromJson(
                resp.body,
                RouteSearchRs::class.java
            )
        } catch (e: Exception) {
            log.warn(e.message)
            throw e
        }
    }

    private fun findRoutesEntityByTitle(
        stationName: String,
        stationType: List<String> = listOf(
            "platform",
            "train_station",
            "station"
        )
    ): RoutesEntity {
        return routesRepository.findFirstByTitleAndStationTypeInIgnoreCase(
            stationName,
            stationType
        )
    }
}