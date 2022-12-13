package com.example.models

import io.ktor.server.util.*
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable


class Card(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Card>(cards)
    var title by cards.title
    var info by cards.info
    var image by cards.image
    var contact by cards.contact

}
object cards : IntIdTable() {
    val title = varchar("title", 40)
    val info = varchar("info",250)
    val contact = varchar("telegram", 50)
    val image= varchar("image",500)
}