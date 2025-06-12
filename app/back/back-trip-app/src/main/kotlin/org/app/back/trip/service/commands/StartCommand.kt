package org.app.back.trip.service.commands

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow

@Component
class StartCommand() : Command {
    override fun apply(update: Update): SendMessage {
        return SendMessage.builder()
            .chatId(update.message.chatId.toString())
            .text("Выберите свой часовой пояс ниже :")
            .replyMarkup(createInlineKeyboardMarkup())
            .build()
        }

    private fun createInlineKeyboardMarkup(): InlineKeyboardMarkup {
        val tzText = listOf(
            listOf("UTC+2", "\uD83D\uDD70 UTC+2 Kaliningrad"),
            listOf("UTC+3", "\uD83D\uDD70 UTC+3 Moscow"),
            listOf("UTC+4", "\uD83D\uDD70 UTC+4 Samara"),
            listOf("UTC+5", "\uD83D\uDD70 UTC+5 Yekaterinburg"),
            listOf("UTC+6", "\uD83D\uDD70 UTC+6 Omsk"),
            listOf("UTC+7", "\uD83D\uDD70 UTC+7 Krasnoyarsk"),
            listOf("UTC+8", "\uD83D\uDD70 UTC+8 Irkutsk"),
            listOf("UTC+9", "\uD83D\uDD70 UTC+9 Chita"),
            listOf("UTC+10", "\uD83D\uDD70 UTC+10 Vladivostok"),
            listOf("UTC+11", "\uD83D\uDD70 UTC+11 Magadan"),
            listOf("UTC+12", "\uD83D\uDD70 UTC+12 Petropavlovsk-Kamchatsky"),
        )

        val tzKeyboard = InlineKeyboardMarkup.builder()
            .keyboard(
                tzText.map {
                    InlineKeyboardRow(
                        InlineKeyboardButton.builder()
                            .callbackData("/tz ${it[0]}")
                            .text(it[1])
                            .build()
                    )
                }
            ).build()

        return tzKeyboard
    }
}