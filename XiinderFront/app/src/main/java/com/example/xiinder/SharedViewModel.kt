package com.example.xiinder

import androidx.lifecycle.ViewModel
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*

class SharedViewModel : ViewModel() {
    var client=HttpClient(CIO)
    {
        install(WebSockets)
        install(ContentNegotiation) {
            json()
        }
        install(HttpTimeout)
    }
    private lateinit var storeToken:StoreToken
    private lateinit var userName:String
    fun setTokenStorage(storeToken: StoreToken,username:String)
    {
        this.storeToken=storeToken
        this.userName=username
    }
    suspend fun getToken(): String?
    {
        return storeToken.getToken()
    }
    suspend fun getUsername(): String?
    {
        return userName
    }
    fun configAuthClient(token:Token)
    {
        client.config { install(Auth)
        {
            bearer {
                loadTokens {
                    BearerTokens(token.tokenData,token.tokenData)
                }
            }
        }}
    }
    suspend fun clientCheck(token: Token)
    {
        client.get("http://192.168.0.10:8888/hello"){
            bearerAuth(token.tokenData)
        }
    }

}