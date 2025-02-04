package org.app.back.trip.manager.dto

import org.app.back.trip.manager.enums.TransportType
import java.text.SimpleDateFormat
import java.util.*

class RoutesInfoRq(
    val to: String,
    val from: String,
    val date: String? = SimpleDateFormat("yyyy-MM-dd").format(Date(System.currentTimeMillis())).toString(),
    val tz: String?,
    val transportType: String? = TransportType.SUBURBAN.name
) {
    override fun toString(): String {
        return "\nRQ\nto: ${this.to}\nfrom: ${this.from}\ndate: ${this.date}"
    }
}