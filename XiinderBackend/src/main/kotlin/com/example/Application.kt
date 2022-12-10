package com.example

import DateAsLongSerializer
import com.example.database.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.netty.*
import com.example.plugins.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.modules.SerializersModule

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused")
fun Application.module() {

    install(ContentNegotiation)
    {
        json()
    }
    SerializersModule {
        DateAsLongSerializer
    }
    DatabaseFactory.init(environment.config)
    configureSecurity()
    configureRouting()
}
