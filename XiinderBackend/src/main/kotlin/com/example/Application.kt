package com.example

import com.example.database.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.netty.*
import com.example.plugins.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    DatabaseFactory.init(environment.config)
    configureSecurity()
    configureRouting()
}
