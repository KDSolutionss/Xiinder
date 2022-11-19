package com.example.xiinder.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.xiinder.R
import com.example.xiinder.Token
import com.example.xiinder.User
import com.example.xiinder.databinding.FragmentAuthBinding
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class AuthFragment : Fragment() {
    var binding:FragmentAuthBinding?=null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding=FragmentAuthBinding.inflate(inflater,container,false)
        binding=fragmentBinding
        binding!!.authorize.setOnClickListener {
            lifecycleScope.launch {goToMain(binding!!.emailText.text.toString(),binding!!.passwordText.text.toString())  }


        }
        binding!!.signup.setOnClickListener { register() }
        return fragmentBinding.root

    }

    private fun register() {
        findNavController().navigate(R.id.action_authFragment_to_signUpFragment)
    }

    private suspend fun goToMain(email:String, password: String) {
        if (check(email,password))
        {
            findNavController().navigate(R.id.action_authFragment_to_startFragment)
        }

    }

    private suspend fun check(email:String, password:String):Boolean
    {
        val user= User(email,password)
        val client = HttpClient(CIO)
        {
            install(WebSockets)
            install(ContentNegotiation) {
                json()
            }
        }

        val message = client.post("https://xiinder.herokuapp.com/login"){ // or your data class
                contentType(ContentType.Application.Json)
                setBody(user)
            }
        val token= message.body<Map<String,String>>()["token"]?.let { Token(it) }
        println(token!!.tokenData)
        client.config { install(Auth)
        {
            bearer {
                loadTokens {
                    BearerTokens(token.tokenData,token.tokenData)
                }

            }
        }}
        val check=client.get("https://xiinder.herokuapp.com/hello"){
            bearerAuth(token.tokenData)
        }
        println(check.body<String>())




        return true
    }
}
