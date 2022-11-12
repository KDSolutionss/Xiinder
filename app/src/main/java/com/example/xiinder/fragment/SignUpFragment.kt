package com.example.xiinder.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.xiinder.R
import com.example.xiinder.binding
import com.example.xiinder.databinding.FragmentAuthBinding
import com.example.xiinder.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {
    var signUpBinding:FragmentSignUpBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding= FragmentSignUpBinding.inflate(inflater,container,false)
        signUpBinding=fragmentBinding
        signUpBinding!!.createAccount.setOnClickListener { completeRegistration(signUpBinding!!.email.text.toString(),
            signUpBinding!!.password.text.toString()) }
        return fragmentBinding.root
    }
    private fun completeRegistration(email:String,password:String) {
       if (check(email,password))
       {
           findNavController().navigate(R.id.action_signUpFragment_to_authFragment)
       }
        else
       {
           Toast.makeText(requireContext(),"lessgo",Toast.LENGTH_SHORT).show()
       }
    }
    private fun check(email:String,password:String):Boolean
    {
        return true
    }

}