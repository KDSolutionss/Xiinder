package com.example.xiinder.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.xiinder.*
import com.example.xiinder.databinding.FragmentSignUpBinding
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class SignUpFragment : Fragment() {
    var signUpBinding: com.example.xiinder.databinding.FragmentSignUpBinding?=null
    lateinit var dataStore:StoreToken
    private val viewModel: SharedViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding= FragmentSignUpBinding.inflate(inflater,container,false)
        signUpBinding=fragmentBinding
        val formatter=DateTimeFormatter.ofPattern("yyyy.MM.dd").withLocale(Locale.getDefault())
        signUpBinding!!.createAccount.setOnClickListener {
            lifecycleScope.launch {completeRegistration(
                signUpBinding!!.email.text.toString(),
                signUpBinding!!.password.text.toString(),
                signUpBinding!!.name.text.toString(),
                LocalDate.parse(signUpBinding!!.editTextDate2.text.toString(),formatter),
                signUpBinding!!.telegramId.text.toString())} }
        dataStore= context?.let { StoreToken(it) }!!
        return fragmentBinding.root
    }
    private suspend fun completeRegistration(email:String, password:String,name:String,date: LocalDate,telegramId:String) {
       if (check(email,password,name,date,telegramId))
       {
           findNavController().navigate(R.id.action_signUpFragment_to_startFragment)
       }
    }
    private suspend fun check(
        email: String,
        password: String,
        name: String,
        date: LocalDate,
        telegramId: String
    ):Boolean
    {
        val usver=UserRegister(email,password,name, date,telegramId)
        val message = viewModel.client.post("http://192.168.0.10:8888/register"){ // or your data class
            contentType(ContentType.Application.Json)
            setBody(usver)
        }
        message.status.value

        when (message.status.value)
        {
            201->
            {
                val token= message.body<Map<String,String>>()["token"]?.let { Token(it) }
                viewModel.configAuthClient(token!!)
                coroutineScope { dataStore.saveToken(token.tokenData) }
                viewModel.setTokenStorage(storeToken = dataStore,usver.username)
                return true
            }
            409->
            {
                Toast.makeText(requireContext(),"already Registered",Toast.LENGTH_SHORT).show();
                findNavController().navigate(R.id.action_signUpFragment_to_authFragment);
                return false
            }
            500->
            {
                Toast.makeText(requireContext(),"server error",Toast.LENGTH_SHORT).show();
                return false
            }
            else->return false
        }
    }

}