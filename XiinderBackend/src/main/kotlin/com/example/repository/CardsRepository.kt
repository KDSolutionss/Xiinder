package com.example.repository

import com.example.models.Card
import com.example.models.User


interface CardsRepository {
    fun add(card: Card) : Card
    fun getBunchOfCards(name: String):List<Card>
}