package com.example

import com.example.dao.CardsDAO
import com.example.models.Card
import com.example.plugins.CardItem
import com.example.repository.CardsRepository
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.transactions.transaction

class CardService {
    private val cardRepository: CardsRepository = CardsDAO()
    fun addCard(card: CardItem): Boolean {
        transaction {   Card.new { image=card.image;info=card.info;title=card.title;contact=card.contacts }  }
        return true
    }
    fun getCards():List<Card>
    {
        return cardRepository.getBunchOfCards()
    }
}