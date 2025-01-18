package org.app.back.trip.service.handler

import mu.KotlinLogging
import org.app.back.trip.config.properties.BackTripAppProperties
import org.app.back.trip.service.buttons.Button
import org.app.back.trip.service.buttons.TimeZoneButton
import org.app.back.trip.service.commands.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class CommandHandler @Autowired constructor(
    properties: BackTripAppProperties,
    private val startCommand: StartCommand,
    private val statusCommand: StatusCommand,
    private val tripCommand: TripCommand,
    private val userCommand: UserCommand,
    private val timeZoneButton: TimeZoneButton,

) {
    val log = KotlinLogging.logger {  }
    val commands = HashMap<String, Command>().run {
        mapOf(
            "/start" to startCommand,
            "/status" to statusCommand,
            "/trip" to tripCommand,
            "/user" to userCommand
        )
    }

    val buttons = HashMap<String, Button>().run {
        mapOf(
            "/tz" to timeZoneButton
        )
    }

    fun runCommand(update: Update) : SendMessage? {
        return try {
            if (update.hasMessage() && update.message.hasText()) {
                val message = update.message
                val command = message.text.split("\\s+".toRegex(), limit = 2).first()
                log.info(message.text)
                commands[command]?.apply(
                    update.apply {
                        message.text = removePrefix(this.message.text, command)
                    }
                ) ?: tripCommand.apply(update)
            } else if (update.hasCallbackQuery()) {
                log.info(update.callbackQuery.data)
                val command = update.callbackQuery.data.split(" ").first()
                buttons[command]!!.apply(update)
            } else {
                throw Exception()
            }
        } catch (e: Exception) {
            val warnMessage = "$e: Unable to determine command"
            log.warn(warnMessage)
            throw Exception(warnMessage)
        }
    }

    private fun removePrefix(text: String, prefix: String): String {
        val trim = text.removePrefix(prefix).trim()
        return trim
    }
}