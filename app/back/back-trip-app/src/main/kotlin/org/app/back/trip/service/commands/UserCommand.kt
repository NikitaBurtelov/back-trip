package org.app.back.trip.service.commands

import org.app.back.trip.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class UserCommand @Autowired constructor(
    private val userService: UserService
): Command {
    override fun apply(update: Update): SendMessage {
        val chatId = update.message.chatId.toString()

        val textMessage = "Save"
        return SendMessage(chatId, textMessage)
    }
}