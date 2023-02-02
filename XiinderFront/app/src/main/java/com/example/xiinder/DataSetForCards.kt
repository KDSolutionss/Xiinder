package com.example.xiinder

import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import com.example.xiinder.network.CardInfo
import com.example.xiinder.network.ProfileInfo
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.*
import kotlinx.coroutines.coroutineScope

class DataSetForCards {
    suspend fun loadCards(viewModel:SharedViewModel):Map<Int, CardInfo>
    {
        val message = viewModel.client.get("http://192.168.0.10:8888/get_cards"){ // or your data class
            contentType(ContentType.Application.Json)
            setBody(viewModel.getUsername())
            timeout { requestTimeoutMillis=10000 }
        }
        when (message.status.value)
        {
            200->
            {
                var a=message.body<List<Card>>()
                print(a)
                a.map { CardInfo(0,1,it.title,it.info,R.drawable.mobile_01) }
                return a.associate { a.indexOf(it) to CardInfo(0, 1, it.title, it.info, R.drawable.mobile_01) }
//                return hashMapOf(
//                    0 to CardInfo(0,1,"Ищу сокомандника на icpc", "Let's go drink",R.drawable.mobile_01),
//                    1 to CardInfo(1,2,"Помогите с задачей по матанализу", "Let's go study",R.drawable.mobile_02),
//                    2 to CardInfo(2,3,"Продаю операционные системы Танненбаума", "Let's go sing",R.drawable.mobile_03),
//                    3 to CardInfo(3,4,"Нужен человек, знающий js, для работы в проекте", "Let's go swim",R.drawable.mobile_04),
//                    4 to CardInfo(4,5,"Придумайте заголовки за меня", "Let's go eat",R.drawable.mobile_05),
//                    5 to CardInfo(5,6,"Ваше такси до Верхней Пышмы", "Let's go",R.drawable.mobile_06)

            }
            401->
            {
                return hashMapOf()
            }
            else ->{
                return hashMapOf()}
        }

    }
}