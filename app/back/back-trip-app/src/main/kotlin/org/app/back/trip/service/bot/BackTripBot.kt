package org.app.back.trip.service.bot

import jakarta.annotation.PostConstruct
import mu.KotlinLogging
import org.app.back.trip.config.properties.BackTripAppProperties
import org.app.back.trip.exceptions.UserRequestException
import org.app.back.trip.service.handler.CommandHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.generics.TelegramClient


@Component
class BackTripBot @Autowired constructor(
    private val properties: BackTripAppProperties,
    private val commandHandler: CommandHandler
) : SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {
    val log = KotlinLogging.logger {  }

    private lateinit var telegramClient: TelegramClient

    @PostConstruct
    fun initClient() {
        telegramClient = OkHttpTelegramClient(botToken)
        log.info { "OkHttpTelegramClient инициализирован" }
    }

    override fun getBotToken(): String = properties.key

    override fun getUpdatesConsumer(): LongPollingUpdateConsumer {
        return this
    }

    override fun consume(update: Update) {
        try {
            val message = commandHandler.runCommand(update)
            telegramClient.execute(message)
        } catch (e: UserRequestException) {
            telegramClient.execute(e.systemResponse())
        } catch (e: Exception) {
            val chatId = update.message.chatId.toString()
            log.warn { e.message }
            telegramClient.execute(
                SendMessage(
                    chatId,
                    "⚠\uFE0F произошла непредвиденная ошибка"
                )
            )
        }
    }

//    override fun consume(update: Update) {
//        if (update.hasMessage() && update.message.hasText()) {
//            val message = SendMessage.builder()
//                .chatId(update.message.chatId.toString())
//                .text("Привет, это ответ от нового бота!")
//                .build()
//
//            try {
//                telegramClient.execute(message)
//            } catch (e: TelegramApiException) {
//                e.printStackTrace()
//            }
//        }
//    }
}