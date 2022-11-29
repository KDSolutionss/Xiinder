package com.example

import com.example.dao.UserLoginInfoDAO
import com.example.dao.UserDAO
import com.example.models.User
import com.example.plugins.UserRegister
import com.example.repository.UserLoginInfoRepository
import com.example.repository.UserRepository
import kotlinx.coroutines.runBlocking

class LoginService {
    private val loginRepository: UserLoginInfoRepository = UserLoginInfoDAO()
    private val userRepository : UserRepository = UserDAO()
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
        val user = runBlocking { userRepository.add( ) } ?: throw Exception() //todo
        runBlocking { loginRepository.add(user.id, userRegister.username, userRegister.password) }
        return true
    }
}