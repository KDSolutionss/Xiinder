package com.example.xiinder.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.xiinder.*
import com.example.xiinder.databinding.FragmentAuthBinding
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class AuthFragment : Fragment() {
    var binding:FragmentAuthBinding?=null
    lateinit var dataStore:StoreToken
    private val viewModel:SharedViewModel by activityViewModels()

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
        dataStore= context?.let { StoreToken(it) }!!

        return   fragmentBinding.root

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
        val message = viewModel.client.post("http://192.168.0.104:8888/login"){ // or your data class
                contentType(ContentType.Application.Json)
                setBody(user)
            }
        when (message.status.value)
        {
            200->
            {
                val token= message.body<Map<String,String>>()["token"]?.let { Token(it) }
                viewModel.configAuthClient(token!!)
                coroutineScope { dataStore.saveToken(token.tokenData) }
                viewModel.setTokenStorage(storeToken = dataStore)
                return true
            }
            401->
            {
                Toast.makeText(requireContext(),"Wrong username or password", Toast.LENGTH_SHORT).show();
                return false
            }
            else ->{Toast.makeText(requireContext(),message.status.value.toString(), Toast.LENGTH_SHORT).show();return false}
        }
    }
}
