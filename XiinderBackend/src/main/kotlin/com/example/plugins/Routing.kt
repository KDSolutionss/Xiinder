package com.example.plugins

import com.example.CardService
import com.example.dao.CardsDAO
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable


@Serializable
data class CardItem(val namee:String,val title:String, val info:String, val contacts:String, val image:String)
fun Application.configureRouting() {
    routing {
        authorizedRouting()
        unauthorizedRouting()
        cardsRouting()
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
fun Route.cardsRouting()
{
    get("/get_cards")
    {
        val cards=CardService().getCards(call.receiveText())
        if (cards != null) {
            call.respond(cards.map {CardItem(it.namee,it.title,it.info,it.contact,it.image) })
        }
    }
    post("/add_card") {
        val cardItem=call.receive<CardItem>()
        CardService().addCard(cardItem)
    }
}

