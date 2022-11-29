package com.example.repository

import com.example.models.UserLoginInfo
import org.jetbrains.exposed.dao.id.EntityID

interface UserLoginInfoRepository {
    fun getById(id: Int): UserLoginInfo?
     fun getByUsername(username: String): UserLoginInfo?
     fun add(userId: EntityID<Int>, username: String, password: String ): UserLoginInfo?
}