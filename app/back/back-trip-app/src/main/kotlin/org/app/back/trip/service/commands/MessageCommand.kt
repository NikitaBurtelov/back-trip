package org.app.back.trip.service.commands

import mu.KotlinLogging
import org.app.back.trip.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

class MessageCommand @Autowired constructor(
    private val userService: UserService
): Command {
    val log = KotlinLogging.logger {  }

    //TODO("Not yet implemented")
    override fun apply(update: Update): SendMessage {
        val chatId = update.message.chatId
        val isAdmin = userService.isAdmin(chatId)
        if (isAdmin) {
            val users = userService
            return SendMessage("message", chatId.toString())
        } else {
            val message = "User is not admin"
            log.info(message)
            return SendMessage(message, chatId.toString())
        }
    }

}