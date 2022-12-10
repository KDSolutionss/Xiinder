package com.example.xiinder.network

import androidx.annotation.DrawableRes

data class CardInfo(
    val cardId: Int,
    val userId: Int,
    val title: String,
    val description: String,
    @DrawableRes val imageId: Int
)