package com.example.repository

import com.example.models.Card


interface CardsRepository {
    fun add(card: Card) : Card
    fun getBunchOfCards():List<Card>
}