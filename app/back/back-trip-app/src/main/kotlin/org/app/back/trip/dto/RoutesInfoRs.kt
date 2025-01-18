package org.app.back.trip.dto

data class RoutesInfoRs(
    val to: String,
    val from: String,
    val routes: List<Routes>
)

data class Routes(
    val transportType: String,
    val departure: String,
    val arrival: String
)