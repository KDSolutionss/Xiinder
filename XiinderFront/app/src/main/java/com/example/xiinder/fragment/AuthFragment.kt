package com.example.xiinder.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.xiinder.R
import com.example.xiinder.databinding.FragmentAuthBinding


class AuthFragment : Fragment() {
    var binding:FragmentAuthBinding?=null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding=FragmentAuthBinding.inflate(inflater,container,false)
        binding=fragmentBinding
        binding!!.authorize.setOnClickListener {
            if (binding!!.emailText.text?.isNotEmpty()!! &&!binding!!.passwordText.text?.isNotEmpty()!!)
            {
                goToMain(binding!!.emailText.text.toString(),binding!!.passwordText.text.toString())
            }

        }
        binding!!.signup.setOnClickListener { register() }
        return fragmentBinding.root

    }

    private fun register() {
        findNavController().navigate(R.id.action_authFragment_to_signUpFragment)
    }

    private fun goToMain(email:String,password: String) {
        if (check(email,password))
        {
            findNavController().navigate(R.id.action_authFragment_to_startFragment)
        }

    }
    private fun check(email:String,password:String):Boolean
    {
        return true
    }
}