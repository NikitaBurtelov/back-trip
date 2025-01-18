package org.app.back.trip.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.telegram.telegrambots.bots.DefaultBotOptions

@ConfigurationProperties(prefix = "tg.bot.settings")
class BackTripBotSettingsProperties {
    lateinit var proxyType: DefaultBotOptions.ProxyType
    lateinit var getUpdatesTimeout: String
    lateinit var getUpdatesLimit: String
}