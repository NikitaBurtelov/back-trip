package org.app.back.trip.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.apache.http.client.config.RequestConfig
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import org.telegram.telegrambots.bots.DefaultBotOptions

@Configuration
class BackTripConfig {

    @Bean
    fun restTemplate(): RestTemplate {
        val connectionManager = PoolingHttpClientConnectionManager()
        connectionManager.maxTotal = 200
        connectionManager.defaultMaxPerRoute = 20

        val requestConfig = RequestConfig.custom()
            .setConnectTimeout(10_000)
            .setSocketTimeout(10_000)
            .setConnectionRequestTimeout(5_000)
            .build()

        val httpClient: CloseableHttpClient = HttpClients.custom()
            .setConnectionManager(connectionManager)
            .setDefaultRequestConfig(requestConfig)
            .setRetryHandler { exception, executionCount, context ->
                if (executionCount > 1) {
                    return@setRetryHandler false
                }
                exception is org.apache.http.NoHttpResponseException
            }
            .build()

        val factory = HttpComponentsClientHttpRequestFactory(httpClient)
        return RestTemplate(factory)
    }

    @Bean
    fun objectMapper() = ObjectMapper().registerModule(KotlinModule())!!

    @Bean
    fun defaultBotOptions() = DefaultBotOptions().apply {
        this.proxyType = DefaultBotOptions.ProxyType.NO_PROXY
        this.getUpdatesTimeout = 50
        this.getUpdatesLimit = 100
    }
}