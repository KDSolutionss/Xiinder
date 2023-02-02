package com.example.database

import com.example.models.UserLoginInfos
import com.example.models.Users
import com.example.models.cards
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init(config: ApplicationConfig){
        val jdbcUrl = config.property("database.jdbcURL").getString()
        val driverClassName = config.property("database.driverClassName").getString()
        val username = config.property("database.username").getString()
        val password = config.property("database.password").getString()
        val database = Database.connect(jdbcUrl, driverClassName, username, password)
        transaction(database) {
            SchemaUtils.create(Users)
            SchemaUtils.create(UserLoginInfos)
            SchemaUtils.create(cards)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}