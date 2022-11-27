package com.example.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

class User(id: EntityID<Int>) : IntEntity(id){
    companion object: IntEntityClass<User>(Users)
}

object Users : IntIdTable(){
}

