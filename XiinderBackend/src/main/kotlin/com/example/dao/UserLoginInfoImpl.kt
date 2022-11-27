package com.example.dao

import com.example.database.DatabaseFactory.dbQuery
import com.example.models.UserLoginInfo
import com.example.models.UserLoginInfos
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class UserLoginInfoImpl : UserLoginInfoFacade {

    override suspend fun all(): List<UserLoginInfo> = dbQuery {
        UserLoginInfos.selectAll().map(::resultRowToUserLoginInfo)
    }


    override suspend fun getById(id: Int) = dbQuery {
        UserLoginInfos
            .select { UserLoginInfos.id eq id }
            .map(::resultRowToUserLoginInfo)
            .singleOrNull()
    }

    override suspend fun getByUsername(username: String) = dbQuery {
        UserLoginInfos
            .select { UserLoginInfos.username eq username }
            .map(::resultRowToUserLoginInfo)
            .singleOrNull()
    }

    override suspend fun add(userId: EntityID<Int>, username: String, password: String) = dbQuery {
        UserLoginInfo.new {
            this.userId = userId
            this.username = username
            this.password = password
        }
    }


    private fun resultRowToUserLoginInfo(row: ResultRow) = UserLoginInfo.wrapRow(row)
}