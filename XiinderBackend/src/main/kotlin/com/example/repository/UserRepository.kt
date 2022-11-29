package com.example.repository

import com.example.models.User

interface UserRepository {
    fun add(user: User) : User
}