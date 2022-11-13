package com.example.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*

fun Application.configureRouting() {
    routing {
        authorizedRouting()
        unauthorizedRouting()
    }
}

fun Route.authorizedRouting() {
    authenticate("auth-jwt") {
        get("/secret") {
            call.respondText("secret")
        }
    }
}

fun Route.unauthorizedRouting() {
    get("/hello-world") {
        call.respondText("Hello, world!")
    }
}

