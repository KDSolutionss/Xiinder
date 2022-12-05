package com.example.xiinder

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
class StoreToken(private val context:Context) {

    companion object {
        private val Context.dataStore:DataStore<Preferences> by preferencesDataStore("tokens")
        val token_key = stringPreferencesKey("token")
    }


    suspend fun getToken(): String?
    {
       val preferences=context.dataStore.data.first()
        return preferences[token_key]
    }


    suspend fun saveToken(name: String) {
        context.dataStore.edit { preferences ->
            preferences[token_key] = name
        }
    }
}