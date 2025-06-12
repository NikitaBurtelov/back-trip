package org.app.back.trip.db

import jakarta.persistence.*

@Entity
@Table(name = "users", schema = "public")
class UserEntity(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(name = "chat_id")
    val chatId: Long,
    @Column(name = "tz")
    val tz: String,
    @Column(name = "is_admin")
    val isAdmin: Boolean
)