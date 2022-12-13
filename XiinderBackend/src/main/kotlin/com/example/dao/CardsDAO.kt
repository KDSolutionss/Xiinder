package com.example.dao

import com.example.models.Card
import com.example.models.cards
import com.example.models.User
import com.example.repository.CardsRepository
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class CardsDAO:CardsRepository {
    override fun add(card: Card)=
        transaction {
            Card.new { image=card.image;info=card.info;title=card.title;contact=card.contact }
        }

    override fun getBunchOfCards()= transaction { cards.selectAll().limit(10).let { Card.wrapRows(it) }.toList() }

}