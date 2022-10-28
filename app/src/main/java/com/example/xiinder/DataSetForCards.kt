package com.example.xiinder

import com.example.xiinder.network.CardInfo

class DataSetForCards {
    fun loadCards():List<CardInfo>
    {
        return listOf<CardInfo>(
            CardInfo(10,1,"Let's go drink",R.drawable.mobile_01),
            CardInfo(10,2,"Let's go study",R.drawable.mobile_02),
            CardInfo(10,3,"Let's go sing",R.drawable.mobile_03),
            CardInfo(10,4,"Let's go swim",R.drawable.mobile_04),
            CardInfo(10,5,"Let's go eat",R.drawable.mobile_05),
            CardInfo(10,6,"Let's go",R.drawable.mobile_06)
        )
    }
}