package org.app.back.trip.manager.service

import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import mu.KotlinLogging
import org.app.back.trip.manager.config.properties.BackTripManagerProperties
import org.app.back.trip.manager.db.domain.RoutesEntity
import org.app.back.trip.manager.dto.RouteSearchRs
import org.app.back.trip.manager.dto.RoutesInfoRq
import org.app.back.trip.manager.repository.RoutesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
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
            val gson = GsonBuilder().setPrettyPrinting().create()

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

            if (resp.statusCode != HttpStatus.OK) {
                log.warn("Error from service: ${resp.body}")
                throw RuntimeException("Ошибка от API: ${resp.body}")
            }

            return gson.fromJson(
                resp.body,
                RouteSearchRs::class.java
            )
        } catch (e: HttpClientErrorException) {
            log.error("HTTP error: ${e.statusCode}. Response body: ${e.responseBodyAsString}", e)
            throw e
        } catch (e: JsonSyntaxException) {
            log.error("JSON parsing error: ${e.message}", e)
            throw e
        } catch (e: Exception) {
            log.error("Unexpected error occurred: ${e.message}", e)
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