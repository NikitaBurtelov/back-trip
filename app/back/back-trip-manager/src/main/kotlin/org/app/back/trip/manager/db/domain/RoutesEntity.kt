package org.app.back.trip.manager.db.domain

import jakarta.persistence.*

@Entity
@Table(name = "routes", schema = "public")
class RoutesEntity(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(name = "code_station")
    val codeStation: String,
    @Column(name = "title")
    val title: String,
    @Column(name = "station_type")
    val stationType: String
)