package org.app.back.trip.manager.service

import com.google.gson.GsonBuilder
import org.app.back.trip.manager.config.BackTripManagerProperties
import org.app.back.trip.manager.db.domain.RoutesEntity
import org.app.back.trip.manager.dto.RouteSearchRq
import org.app.back.trip.manager.dto.RouteSearchRs
import org.app.back.trip.manager.dto.RoutesInfoRq
import org.app.back.trip.manager.dto.RoutesInfoRs
import org.app.back.trip.manager.repository.RoutesRepository
import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ManagerServiceImpl @Autowired constructor(
    val properties: BackTripManagerProperties,
    val routesRepository: RoutesRepository
): ManagerService {
    fun stationInfo(request: RouteSearchRq): RouteSearchRs {
        val doc = Jsoup.connect(
            properties.baseUrl +
                    "/${properties.version}" +
                    "/search/?" +
                    "apikey=${properties.key}" +
                    "&format=${properties.format}" +
                    "&from=${request.from}" +
                    "&to=${request.to}" +
                    "&lang=${properties.lang}" +
                    "&date=${request.date}"
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
            RouteSearchRs::class.java
        )
    }

    fun routesEntity(stationName: String): RoutesEntity {
        return routesRepository.findByTitleAllIgnoreCase(stationName)
    }

    override fun routesInfo(routesInfoRq: RoutesInfoRq): RoutesInfoRs {
        val departureStationTitle = routesInfoRq.departureStationTitle
        val arrivalStationName = routesInfoRq.arrivalStationName

        val endRouteEntity = routesEntity(arrivalStationName)
        val startRouteEntity = routesEntity(arrivalStationName)



        stationInfo(
            RouteSearchRq(
                to = endRouteEntity.codeStation,
                from = startRouteEntity.codeStation,
                date = routesInfoRq.date!!
            )
        )
    return RoutesInfoRs()
    }
}