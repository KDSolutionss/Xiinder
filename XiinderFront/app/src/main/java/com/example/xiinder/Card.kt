package com.example.xiinder

import kotlinx.serialization.Serializable

@Serializable
data class Card(val title:String, val info:String, val contacts:String, val image:String)
