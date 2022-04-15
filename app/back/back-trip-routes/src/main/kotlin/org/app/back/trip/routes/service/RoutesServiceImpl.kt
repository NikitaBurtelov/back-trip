package org.app.back.trip.routes.service

import org.app.back.trip.routes.db.domain.RoutesEntity
import org.app.back.trip.routes.repository.RoutesRepository
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.IOException
import java.net.URL

@Component
class RoutesServiceImpl @Autowired constructor(
    private val routesRepository: RoutesRepository
): RoutesService {
    @Throws(IOException::class)
    override fun parse(pageUrl: URL): RoutesEntity {
        val doc: Document = Jsoup.connect("https://yandex.ru/")
            .userAgent("Chrome/4.0.249.0 Safari/532.5")
            .referrer("http://www.google.com")
            .get()

        val listNews: Elements = doc.select("div.news__panel.mix-tabber-slide2__panel")
        for (element in listNews.select("a")) {
            element.text()
        }

        return mapping("")
    }

    override fun save(routesEntity: RoutesEntity) {
        TODO("Not yet implemented")
    }

    private fun mapping(element: String): RoutesEntity {
        return RoutesEntity(
            id = 1
        )
    }
}