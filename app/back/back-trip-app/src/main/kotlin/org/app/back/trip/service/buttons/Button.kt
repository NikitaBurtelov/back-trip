package org.app.back.trip.service.buttons

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

interface Button {
    fun apply(update: Update): SendMessage
}