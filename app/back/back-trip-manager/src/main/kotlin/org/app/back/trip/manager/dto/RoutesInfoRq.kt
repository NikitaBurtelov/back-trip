package org.app.back.trip.manager.dto

import org.app.back.trip.manager.enum.TransportType
import java.text.SimpleDateFormat
import java.util.*

class RoutesInfoRq(
    val departureStationTitle: String,
    val arrivalStationName: String,
    //TODO учитывать таймзон
    val date: String? = SimpleDateFormat("yyyy-MM-dd").format(Date(System.currentTimeMillis())).toString(),
    val timeZone: TimeZone? = null,
    val transportType: String? = TransportType.SUBURBAN.name,
)