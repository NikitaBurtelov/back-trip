package org.app.back.trip.service.bot

import mu.KotlinLogging
import org.app.back.trip.config.properties.BackTripAppProperties
import org.app.back.trip.service.handler.CommandHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class BackTripBot @Autowired constructor(
    private val properties: BackTripAppProperties,
    private val commandHandler: CommandHandler,
    botOptions: DefaultBotOptions
) : TelegramLongPollingBot(botOptions, properties.key) {
    val log = KotlinLogging.logger {  }

    override fun getBotUsername(): String {
        return properties.name
    }

    override fun onUpdateReceived(update: Update) {
        try {
            val message = commandHandler.runCommand(update)
            execute(message)
        } catch (e: Exception) {
            val chatId = update.message.chatId.toString()
            log.warn { e.message }
            execute(SendMessage(chatId, "Error"))
        }
    }
}