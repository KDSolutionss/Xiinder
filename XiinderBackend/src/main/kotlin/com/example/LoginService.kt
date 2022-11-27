package com.example

import com.example.dao.UserLoginInfoImpl
import com.example.dao.UsersDAO
import com.example.plugins.UserRegister
import kotlinx.coroutines.runBlocking

class LoginService {
    private val loginDao = UserLoginInfoImpl()
    private val usersDao = UsersDAO()
    fun isValidUserAuth(username: String, password: String): Boolean {
        val loginInfo = runBlocking {loginDao.getByUsername(username) } ?: return false
        return loginInfo.password == password
    }

    fun userExists(username: String): Boolean {
        val userInfo = runBlocking { loginDao.getByUsername(username) }
        userInfo ?: return false
        return true
    }

    fun register(userRegister: UserRegister): Boolean {
        val user = runBlocking { usersDao.add() } ?: throw Exception()
        runBlocking { loginDao.add(user.id, userRegister.username, userRegister.password) }
        return true
    }
}