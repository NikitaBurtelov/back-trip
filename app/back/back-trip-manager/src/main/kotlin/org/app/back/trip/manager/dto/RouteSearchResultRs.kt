package org.app.back.trip.manager.dto

import com.google.gson.annotations.SerializedName

class RouteSearchResult(
    val pagination: Pagination,
    @SerializedName("interval_segments")
    val intervalSegments: List<IntervalSegment>,
    val segments: List<Segment>,
    val search: Search
)

class Pagination(
    val total: Int,
    val limit: Int,
    val offset: Int
)

class IntervalSegment(
    val from: SegmentsFromOrTo,
    val to: SegmentsFromOrTo,
    val thread: Thread,
    @SerializedName("departure_platfrorm")
    val departurePlatform: String? = null,
    val stops: String,
    @SerializedName("departure_terminal")
    val departure_terminal: String? = null,
    @SerializedName("has_transfers")
    val hasTransfers: Boolean,
    @SerializedName("tickets_info")
    val ticketsInfo: TicketsInfo,
    val duration: Int,
    @SerializedName("arrival_terminal")
    val arrivalTerminal: String? = null,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("arrival_platform")
    val arrivalPlatform: String
)

class Segment(
    val arrival: String,
    val from: SegmentsFromOrTo,
    val to: String,
    val thread: Thread,
    @SerializedName("departure_platform")
    val departurePlatform: String? = null,
    val departure: String,
    val stops: String,
    @SerializedName("departure_terminal")
    val departureTerminal: String? = null,
    @SerializedName("has_transfers")
    val hasTransfers: Boolean,
    @SerializedName("tickets_info")
    val ticketsInfo: TicketsInfo,
    val duration: Int,
    @SerializedName("arrival_terminal")
    val arrivalTerminal: String? = null,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("arrival_platform")
    val arrivalPlatform: String
)

class TicketsInfo(
    @SerializedName("et_marker")
    val etMarker: Boolean,
    val places: List<Place>
)

class Place(
    val currency: String,
    val price: Price,
    val name: String
)

class Price(
    val cents: Int,
    val whole: Int
)

class SegmentsFromOrTo(
    val code: String,
    val title: String,
    @SerializedName("station_type")
    val stationType: String,
    @SerializedName("station_type_name")
    val stationTypeName: String,
    @SerializedName("popular_title")
    val popularTitle: String,
    @SerializedName("short_title")
    val shortTitle: String,
    @SerializedName("transport_type")
    val transportType: String,
    val type: String
)

class Thread(
    val uid: String,
    val title: String,
    val interval: Interval,
    val number: String,
    @SerializedName("short_title")
    val shortTitle: String,
    @SerializedName("thread_method_link")
    val threadMethodLink: String,
    val carrier: Carrier,
    @SerializedName("transport_type")
    val transportType: String,
    val vehicle: String,
    @SerializedName("transport_subtype")
    val transportSubtype: TransportSubtype,
    @SerializedName("express_type")
    val expressType: String? = null
)

class Interval(
    val density: String,
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("begin_time")
    val beginTime: Int
)

class TransportSubtype(
    val color: String,
    val code: String,
    val title: String
)

class Carrier(
    val code: Int,
    val contacts: String,
    val url: String,
    @SerializedName("logo_svg")
    val logoSvg: String? = null,
    val title: String,
    val phone: String,
    val codes: Codes,
    val address: String,
    val logo: String,
    val email: String
)

class Codes(
    val icao: String? = null,
    val sirena: String? = null,
    val iata: String? = null
)

class Search(
    val date: String,
    val to: SearchFromOrTo,
    val from: SearchFromOrTo
)

class SearchFromOrTo(
    val code: String,
    val type: String,
    @SerializedName("popular_title")
    val popularTitle: String,
    @SerializedName("short_title")
    val shortTitle: String,
    val title: String
)