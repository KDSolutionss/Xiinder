package com.example.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User>(Users)
    var name by Users.name
    var birthDate by Users.birthDate
    var telegramId by Users.telegramId
}

object Users : IntIdTable() {
    val name = varchar("name", 40)
    val birthDate = datetime("birthDate")
    val telegramId = varchar("telegramId", 20)
}

