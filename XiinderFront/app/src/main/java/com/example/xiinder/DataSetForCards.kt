package com.example.xiinder

import com.example.xiinder.network.CardInfo
import com.example.xiinder.network.ProfileInfo

class DataSetForCards {
    fun loadCards():Map<Int, CardInfo>
    {
        return hashMapOf(
            0 to CardInfo(0,1,"Ищу сокомандника на icpc", "Let's go drink",R.drawable.mobile_01),
            1 to CardInfo(1,2,"Помогите с задачей по матанализу", "Let's go study",R.drawable.mobile_02),
            2 to CardInfo(2,3,"Продаю операционные системы Танненбаума", "Let's go sing",R.drawable.mobile_03),
            3 to CardInfo(3,4,"Нужен человек, знающий js, для работы в проекте", "Let's go swim",R.drawable.mobile_04),
            4 to CardInfo(4,5,"Придумайте заголовки за меня", "Let's go eat",R.drawable.mobile_05),
            5 to CardInfo(5,6,"Ваше такси до Верхней Пышмы", "Let's go",R.drawable.mobile_06)
        )
    }
}