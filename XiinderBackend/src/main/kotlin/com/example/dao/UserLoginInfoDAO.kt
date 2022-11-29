package com.example.dao

import com.example.models.UserLoginInfo
import com.example.models.UserLoginInfos
import com.example.repository.UserLoginInfoRepository
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.transactions.transaction

class UserLoginInfoDAO : UserLoginInfoRepository {
    override fun getById(id: Int) = transaction { UserLoginInfo.findById(id) }
    override fun getByUsername(username: String) =
        transaction {
            UserLoginInfo.find { UserLoginInfos.username eq username }.singleOrNull()
        }

    override fun add(userId: EntityID<Int>, username: String, password: String) =
        transaction {
            UserLoginInfo.new {
                this.userId = userId
                this.username = username
                this.password = password
            }
        }


    private fun resultRowToUserLoginInfo(row: ResultRow) = UserLoginInfo.wrapRow(row)
}