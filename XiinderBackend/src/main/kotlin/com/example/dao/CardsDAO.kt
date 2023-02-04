package com.example.dao

import com.example.models.Card
import com.example.models.cards
import com.example.models.User
import com.example.repository.CardsRepository

import org.jetbrains.exposed.sql.SqlExpressionBuilder.neq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class CardsDAO:CardsRepository {
    override fun add(card: Card)=
        transaction {
            Card.new { namee=card.namee;title=card.title;info=card.info;contact=card.contact;image=card.image; }
        }

    override fun getBunchOfCards(name: String)= transaction { cards
        .select(cards.namee neq name)
        .limit(10).let { Card.wrapRows(it) }.toList() }

}