package org.app.back.trip.service.commands

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class StatusCommand: Command {
    override fun apply(update: Update): SendMessage {
        TODO("Not yet implemented")
    }
}