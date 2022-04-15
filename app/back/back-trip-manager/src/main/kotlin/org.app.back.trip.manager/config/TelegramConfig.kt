package org.app.back.trip.manager.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.autoconfigure.cloudfoundry.AccessLevel
import org.springframework.stereotype.Component


@Component
data class TelegramConfig (
    @Value("\${telegram.webhook-path}")
    var webhookPath: String? = null,
    @Value("\${telegram.bot-name}")
    var botName: String? = null,
    @Value("\${telegram.bot-token}")
    var botToken: String? = null
)
