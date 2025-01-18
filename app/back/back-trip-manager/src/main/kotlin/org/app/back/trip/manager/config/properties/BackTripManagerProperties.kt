package org.app.back.trip.manager.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "api")
class BackTripManagerProperties {
    lateinit var baseUrl: String
    lateinit var version: String
    lateinit var key: String
    lateinit var lang: String
    lateinit var format: String
}