package org.app.back.trip.service.commands

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

interface Command {
    fun apply(update: Update): SendMessage
}