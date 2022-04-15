package org.app.back.trip.routes.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "routes")
class BackTripRoutesProperties {
    lateinit var baseUrl: String
}