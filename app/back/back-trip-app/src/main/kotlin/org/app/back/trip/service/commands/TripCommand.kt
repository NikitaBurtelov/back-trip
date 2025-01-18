package org.app.back.trip.service.commands

import mu.KotlinLogging
import org.app.back.trip.config.properties.BackTripAppProperties
import org.app.back.trip.dto.RoutesInfoRq
import org.app.back.trip.dto.RoutesInfoRs
import org.app.back.trip.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Service
class TripCommand @Autowired constructor(
    private val properties: BackTripAppProperties,
    private val restTemplate: RestTemplate,
    private val userService: UserService
): Command {
    val log = KotlinLogging.logger {  }

    override fun apply(update: Update): SendMessage {
        try {
            val message = update.message
            val user = userService.userByChatId(message.chatId).get()
            val data = message.text.split(",")
            val userTz = user.tz
            val userMessageDate = message.date
            val instant = Instant.ofEpochSecond(userMessageDate.toLong())
            val zoneId = ZoneId.of(userTz)
            val dateTimeInZone = instant.atZone(zoneId)
            val formattedDate = dateTimeInZone.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

            val request = RoutesInfoRq(
                from = data[0].strip(),
                to = data[1].strip(),
                tz = userTz,
                date = formattedDate
            )

            val headers = HttpHeaders()
            headers["Content-Type"] = "application/json"

            val response = restTemplate.exchange(
                "${properties.backTripManagerUrl}/api/v1/info",
                HttpMethod.POST,
                HttpEntity(request, headers),
                RoutesInfoRs::class.java
            )
            val routesInfoRs = response.body
                ?: throw IllegalArgumentException("Segments list is empty")

            return SendMessage(
                message.chatId.toString(),
                routesInfoMapping(routesInfoRs, dateTimeInZone)
            )
        } catch (e: Exception) {
            log.warn { e.message }
            throw e
        }
    }

    private fun routesInfoMapping(
        routesInfoRs: RoutesInfoRs,
        userDateTimeInZone: ZonedDateTime
    ): String {
        return "\uD83D\uDC40 Расписание маршрута ${routesInfoRs.from} -> ${routesInfoRs.to}\n"
            .plus(
                routesInfoRs.routes.filter {
                    val zonedDateTime = ZonedDateTime.parse(it.departure, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                    userDateTimeInZone.isBefore(zonedDateTime)
                }.joinToString("\n") {
                    val dateTimeFormatterDeparture = DateTimeFormatter
                        .ofPattern("MM.dd ⌛\uFE0F HH:mm")
                    val dateTimeFormatterArrival = DateTimeFormatter
                        .ofPattern("HH:mm")

                    "\uD83D\uDE82 " +
                            ZonedDateTime.parse(it.departure).format(dateTimeFormatterDeparture) +
                            " - " +
                            ZonedDateTime.parse(it.arrival).format(dateTimeFormatterArrival)
                }
            )
    }
}