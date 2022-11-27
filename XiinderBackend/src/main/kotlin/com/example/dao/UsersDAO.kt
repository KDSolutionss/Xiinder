package com.example.dao

import com.example.database.DatabaseFactory.dbQuery
import com.example.models.User
import com.example.models.Users
import org.jetbrains.exposed.sql.insert

class UsersDAO {
    suspend fun add() = dbQuery {
        Users.insert { }.resultedValues?.singleOrNull()?.let { User.wrapRow(it) }
    }

    fun getById(id: Int) = User.findById(id)
}