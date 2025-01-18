package org.app.back.trip.service.buttons

import org.app.back.trip.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class TimeZoneButton @Autowired constructor(
    val userService: UserService
): Button {
    override fun apply(update: Update): SendMessage {
        val chatId = update.callbackQuery.message.chatId
        val callbackData = update.callbackQuery.data.split(" ")[1]

        val user = userService.userByChatId(chatId)

        if (user.isEmpty)
            userService.user(chatId, callbackData)
        else
            userService.userUpdate(chatId, callbackData)

        return SendMessage(chatId.toString(), "Вы выбрали: $callbackData")
    }
}