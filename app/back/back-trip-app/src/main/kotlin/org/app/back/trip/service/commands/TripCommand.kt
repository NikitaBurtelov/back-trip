package org.app.back.trip.service.commands

import com.google.gson.GsonBuilder
import mu.KotlinLogging
import org.app.back.trip.config.properties.BackTripAppProperties
import org.app.back.trip.dto.ErrorResponse
import org.app.back.trip.dto.RoutesInfoRq
import org.app.back.trip.dto.RoutesInfoRs
import org.app.back.trip.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.*
import org.springframework.web.server.ResponseStatusException
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.EntityNotFoundException

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
                String::class.java
            )
            response.body
                ?: throw IllegalArgumentException("Segments list is empty")

            val gson = GsonBuilder().setPrettyPrinting().create()

            if (response.statusCode.isError) {
                val errorResponse = gson.fromJson(
                    response.body,
                    ErrorResponse::class.java
                )
                throw ResponseStatusException(response.statusCode, "Ошибка API: ${errorResponse.message}")
            } else {
                val routesInfoRs = gson.fromJson(
                    response.body,
                    RoutesInfoRs::class.java
                )
                return SendMessage(
                    message.chatId.toString(),
                    routesInfoMapping(routesInfoRs, dateTimeInZone)
                )
            }
        } catch (e: EntityNotFoundException) {
            log.warn("Entity not found: ${e.message}", e)
            throw e
        } catch (e: IllegalArgumentException) {
            log.warn("Invalid argument: ${e.message}", e)
            throw e
        } catch (e: ResourceAccessException) {
            log.error("Resource access error (timeout or service unavailable): ${e.message}", e)
            throw e
        } catch (e: Exception) {
            log.error("Unknown error: ${e.message}", e)
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