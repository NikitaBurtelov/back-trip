package org.app.back.trip.service.user

import mu.KotlinLogging
import org.app.back.trip.db.UserEntity
import org.app.back.trip.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityNotFoundException

@Service
class UserServiceImpl @Autowired constructor(
    private val userRepository: UserRepository
) : UserService {
    val log = KotlinLogging.logger {  }

    @Transactional
    override fun userUpdate(
        chatId: Long,
        tz: String,
        isAdmin: Boolean
    ) {
        try {
            val user = userRepository.findByChatId(chatId)
                .orElseThrow { throw EntityNotFoundException("User with chatId $chatId not found") }
            val userId = user.id
            userRepository.save(
                UserEntity(
                    id = userId,
                    chatId = chatId,
                    tz = tz,
                    isAdmin = isAdmin
                )
            )
        } catch (e: Exception) {
            log.warn("do not find user chatId $chatId")
            throw EntityNotFoundException("User with chatId $chatId not found")
        }
    }

    @Transactional
    override fun user(chatId: Long, tz: String) {
        userRepository.save(
            UserEntity(
                chatId = chatId,
                tz = tz,
                isAdmin = false
            )
        )
    }

    @Transactional
    override fun user(id: Int): Optional<UserEntity> {
        try {
            return userRepository.findById(id)
        } catch (e: Exception) {
            log.warn("do not find user $id")
            throw EntityNotFoundException("User with id $id not found")
        }
    }

    @Transactional
    override fun users(): MutableList<UserEntity> {
        val users = userRepository.findAll()
        return users
    }

    @Transactional
    override fun userByChatId(id: Long): Optional<UserEntity> {
        val user = userRepository.findByChatId(id)
        return user
    }

    override fun isAdmin(id: Long): Boolean {
        try {
            val user = userRepository.findByChatId(id)
                .orElseThrow { throw EntityNotFoundException("User with id $id not found") }
            return user.isAdmin
        } catch (e: Exception) {
            log.warn("do not find user with id $id")
            throw EntityNotFoundException("User with id $id not found")
        }
    }
}