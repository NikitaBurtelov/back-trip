package org.app.back.trip.routes.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "api")
class BackTripRoutesProperties {
    lateinit var baseUrl: String
    lateinit var version: String
    lateinit var key: String
    lateinit var lang: String
}