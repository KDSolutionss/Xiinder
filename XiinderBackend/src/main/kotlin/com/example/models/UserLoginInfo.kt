package com.example.models

import com.example.plugins.User
import com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import java.util.Date

class UserLoginInfo(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserLoginInfo>(UserLoginInfos)
    var username by UserLoginInfos.username
    var password by UserLoginInfos.password
    var userId by UserLoginInfos.userId
}

object UserLoginInfos : IntIdTable() {
    val userId = reference("userId", Users.id)
    val username = varchar("username", 20)
    val password = varchar("password", 30)
}