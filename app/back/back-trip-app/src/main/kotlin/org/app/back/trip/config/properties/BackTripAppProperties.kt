package org.app.back.trip.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "tg.bot")
class BackTripAppProperties {
    lateinit var key: String
    lateinit var name: String
    lateinit var backTripManagerUrl: String
}