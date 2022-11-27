package com.example.dao

import com.example.models.UserLoginInfo
import org.jetbrains.exposed.dao.id.EntityID

interface UserLoginInfoFacade {
    suspend fun all(): List<UserLoginInfo>
    suspend fun getById(id: Int): UserLoginInfo?
    suspend fun getByUsername(username: String): UserLoginInfo?
    suspend fun add(userId: EntityID<Int>, username: String, password: String ): UserLoginInfo?
}