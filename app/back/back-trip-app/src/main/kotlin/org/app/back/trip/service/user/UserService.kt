package org.app.back.trip.service.user

import org.app.back.trip.db.UserEntity
import java.util.*

interface UserService {
    fun user(chatId: Long, tz: String)
    fun user(id: Int): Optional<UserEntity>
    fun userUpdate(chatId: Long, tz: String, isAdmin: Boolean = false)
    fun users(): MutableList<UserEntity>
    fun userByChatId(id: Long): Optional<UserEntity>
    fun isAdmin(id: Long): Boolean
}