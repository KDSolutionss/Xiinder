package com.example

import com.example.dao.UserLoginInfoDAO
import com.example.models.User
import com.example.plugins.UserRegister
import com.example.repository.UserLoginInfoRepository
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.transactions.transaction

class LoginService {
    private val loginRepository: UserLoginInfoRepository = UserLoginInfoDAO()
    fun isValidUserAuth(username: String, password: String): Boolean {
        val loginInfo = runBlocking { loginRepository.getByUsername(username) } ?: return false
        return loginInfo.password == password
    }

    fun userExists(username: String): Boolean {
        val userInfo = runBlocking { loginRepository.getByUsername(username) }
        userInfo ?: return false
        return true
    }

    fun register(userRegister: UserRegister): Boolean {
        val user = transaction {
            User.new {
                name = userRegister.name
                birthDate = userRegister.birthDate
                telegramId = userRegister.telegramId
            }
        }
        runBlocking { loginRepository.add(user.id, userRegister.username, userRegister.password) }
        return true
    }
}