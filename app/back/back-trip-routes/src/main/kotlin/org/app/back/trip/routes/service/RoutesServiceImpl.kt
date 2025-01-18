package org.app.back.trip.routes.service

import com.google.gson.GsonBuilder
import mu.KotlinLogging
import org.app.back.trip.routes.config.BackTripRoutesProperties
import org.app.back.trip.routes.db.domain.RoutesEntity
import org.app.back.trip.routes.dto.response.StationsRs
import org.app.back.trip.routes.repository.RoutesRepository
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RoutesServiceImpl @Autowired constructor(
    private val properties: BackTripRoutesProperties,
    private val routesRepository: RoutesRepository
): RoutesService {
    val log = KotlinLogging.logger {}

    override fun station(): StationsRs {
        val doc: Document = Jsoup.connect(
            properties.baseUrl +
                    "/${properties.version}" +
                    "/stations_list/?" +
                    "apikey=${properties.key}" +
                    "&lang=${properties.lang}" +
                    "&format=${properties.format}"
        )
            .userAgent("Chrome/4.0.249.0 Safari/532.5")
            .referrer("http://www.google.com")
            .maxBodySize(0)
            .timeout(0)
            .ignoreContentType(true)
            .ignoreHttpErrors(true)
            .get()

        val gson = GsonBuilder().setPrettyPrinting().create()

        return gson.fromJson(
            doc.body().text(),
            StationsRs::class.java
        )
    }

    override fun save() {
        val stationsRs = station()
        log.info("Start")
        stationsRs.countries!!.forEach { countries ->
            countries.regions.forEach { region->
                region.settlements.forEach { settlement->
                    settlement.stations.forEach { station->
                        routesRepository.save(
                            RoutesEntity(
                                codeStation = station.codes.yandexCode!!,
                                title = station.title,
                                stationType = station.stationType!!,
                            )
                        )
                    }
                }
            }
        }
        log.info("Completed")
    }
}