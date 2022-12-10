package com.example.dao

import com.example.models.User
import com.example.models.Users
import com.example.repository.UserRepository
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class UserDAO : UserRepository {
    override fun add(user: User) = transaction {
        User.new {
            name = user.name
            birthDate = user.birthDate
            telegramId = user.telegramId
        }
    }

    fun getById(id: Int) = transaction { User.findById(id) }
}