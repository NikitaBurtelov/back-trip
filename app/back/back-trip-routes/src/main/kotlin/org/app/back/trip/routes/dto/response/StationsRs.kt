package org.app.back.trip.routes.dto.response

import com.google.gson.annotations.SerializedName

class StationsRs(
    val countries: List<Countries>,
)

class Countries(
    val regions: List<Regions>,
    val codes: Codes,
    val title: String
)

class Regions(
    @SerializedName("settlements")
    val settlements: List<Settlements>,
    @SerializedName(value = "codes")
    val codes: Codes,
    @SerializedName("title")
    val title: String?
)

class Settlements(
    val title: String?,
    val codes: Codes,
    val stations: List<Stations>
)

class Stations(
    val direction: String?,
    val codes: Codes,
    @SerializedName(value = "station_type")
    val stationType: String?,
    val title: String,
    val longitude: String?,
    @SerializedName(value = "transport_type")
    val transportType: String?,
    val latitude: String?
)

class Codes(
    @SerializedName(value = "yandex_code")
    val yandexCode: String?,
    @SerializedName(value = "esr_code")
    val esrCode: String?
)