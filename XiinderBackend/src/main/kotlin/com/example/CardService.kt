package com.example

import com.example.dao.CardsDAO
import com.example.dao.UserLoginInfoDAO
import com.example.models.Card
import com.example.plugins.CardItem
import com.example.plugins.User
import com.example.plugins.UserRegister
import com.example.repository.CardsRepository
import com.example.repository.UserLoginInfoRepository
import com.example.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.transactions.transaction

class CardService {
    private val cardRepository: CardsRepository = CardsDAO()
    private val userRepository: UserLoginInfoRepository =UserLoginInfoDAO()
    fun addCard(card: CardItem): Boolean {
        transaction {   Card.new { namee=card.namee;title=card.title;info=card.info;contact=card.contacts;image=card.image; }  }
        return true
    }
    fun getCards(username: String): List<Card>?
    {
        return cardRepository.getBunchOfCards(username)
    }
}