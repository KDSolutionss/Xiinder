@file:UseSerializers(DateAsLongSerializer::class)
package com.example.xiinder

import com.example.xiinder.utils.DateAsLongSerializer
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.Serializable
import java.time.LocalDate
@Serializable
data class UserRegister(val username: String, val password: String, val name: String, val birthDate: LocalDate, val telegramId: String)

