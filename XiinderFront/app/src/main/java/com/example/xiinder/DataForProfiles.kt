package com.example.xiinder

import com.example.xiinder.network.CardInfo
import com.example.xiinder.network.ProfileInfo
import java.util.Dictionary

class DataForProfiles {
    fun getProfilesInfo(): Map<Int, ProfileInfo>{
        return hashMapOf(
            1 to ProfileInfo(1, "Lucy","I like good food and drink.", R.drawable.mobile_01),
            2 to ProfileInfo(2,"Kate","Let's go study",R.drawable.mobile_02),
            3 to ProfileInfo(3,"Sam","Let's go sing",R.drawable.mobile_03),
            4 to ProfileInfo(4,"Josselin","Let's go swim",R.drawable.mobile_04),
            5 to ProfileInfo(5,"Mark","Let's go eat",R.drawable.mobile_05),
            6 to ProfileInfo(6,"Dan","Let's go",R.drawable.mobile_06)
        )
    }
}