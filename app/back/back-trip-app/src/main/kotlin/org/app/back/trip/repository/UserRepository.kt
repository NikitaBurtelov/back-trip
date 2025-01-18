package org.app.back.trip.repository

import org.app.back.trip.db.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<UserEntity, Int> {
    fun findByChatId(id: Long): Optional<UserEntity>
}