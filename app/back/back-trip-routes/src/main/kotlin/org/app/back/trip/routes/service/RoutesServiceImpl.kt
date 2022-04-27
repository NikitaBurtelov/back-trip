package org.app.back.trip.routes.service

//import org.app.back.trip.routes.repository.RoutesRepository

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.GsonBuilder
import mu.KotlinLogging
import org.app.back.trip.routes.config.BackTripRoutesProperties
import org.app.back.trip.routes.db.domain.RoutesEntity
import org.app.back.trip.routes.dto.response.StationsRs
import org.app.back.trip.routes.repository.RoutesRepository
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.*


@Component
class RoutesServiceImpl @Autowired constructor(
    private val backTripRoutesProperties: BackTripRoutesProperties,
    private val routesRepository: RoutesRepository
): RoutesService {
    private val resourceUrl = backTripRoutesProperties.baseUrl+backTripRoutesProperties.version
    val log = KotlinLogging.logger {}

    override fun station(): StationsRs {
        val doc: Document = Jsoup.connect("$resourceUrl/stations_list/?apikey=${backTripRoutesProperties.key}&lang=ru_RU&format=json")
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
        stationsRs.countries.forEach { countries ->
            countries.regions.forEach { region->
                region.settlements.forEach { settlement->
                    settlement.stations!!.forEach { station->
                        routesRepository.save(
                            RoutesEntity(
                                idStation = station!!.codes.yandexCode,
                                title = station.title
                            )
                        )
                    }
                }
            }
        }

    }


//        val code = stationsRs.countries.forEach { countries ->
//            countries.regions.forEach { region->
//                region.settlements.forEach { settlement->
//                    settlement.stations!!.forEach { station->
//                        station.codes.yandexCode
//                    }
//                }
//            }
//        }

        // TODO убрать говнокод
//        for (station in stationsRs.countries) {
//            for (regions in station.regions)
//                for (settlements in regions.settlements)
//                    for (station in settlements.stations!!) {
//                        log.info("${station.codes.yandexCode}")
//                        if (station.codes.yandexCode != null) {
//                            val routesEntity = RoutesEntity(
//                                id_station = station.codes.yandexCode,
//                                title = station.title!!
//                            )
//                            routesRepository.save(routesEntity)
//                            log.info("done")
//                        }
//                        else {
//                            log.info("${station.codes.yandexCode} ${station.title}")
//                        }
//                    }
//        }
}