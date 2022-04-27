package org.app.back.trip.manager.service

import com.google.gson.GsonBuilder
import org.app.back.trip.manager.config.BackTripManagerProperties
import org.app.back.trip.manager.dto.RoutesInfoRq
import org.app.back.trip.manager.dto.RouteSearchResult
import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ManagerServiceImpl @Autowired constructor(
    val properties: BackTripManagerProperties
) {
    fun stationInfo(request: RoutesInfoRq): RouteSearchResult {
        val doc = Jsoup.connect(
            properties.baseUrl +
                    "/${properties.version}" +
                    "/search/?" +
                    "apikey=${properties.key}" +
                    "&format=${properties.format}" +
                    "&from=${request.departureStationTitle}" +
                    "&to=${request.arrivalStationName}" +
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
            RouteSearchResult::class.java
        )
    }

    fun search(request: RoutesInfoRq) {
        val routesInfoRs = stationInfo(request)

    }
}