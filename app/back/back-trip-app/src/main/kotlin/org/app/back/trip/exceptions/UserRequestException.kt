package org.app.back.trip.exceptions

import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class UserRequestException(private val systemResponse: SendMessage): RuntimeException(systemResponse.text) {
    fun systemResponse(): SendMessage {
        return systemResponse
    }
}