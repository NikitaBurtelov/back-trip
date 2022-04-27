package org.app.back.trip.routes.db.domain

import javax.persistence.*

//import javax.persistence.*

@Entity
@Table(name = "routes", schema = "public")
class RoutesEntity(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(name = "id_station")
    val idStation: String? = "test",
    @Column(name = "title")
    val title: String? = "test"
)