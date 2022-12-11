package com.example.xiinder.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.fragment.findNavController
import com.example.xiinder.R

import com.example.xiinder.databinding.FragmentAuthBinding
import com.example.xiinder.databinding.FragmentProfileDetailsBinding
import kotlinx.coroutines.flow.map

class ProfileDetailsFragment : Fragment() {
    var binding: FragmentProfileDetailsBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding=FragmentProfileDetailsBinding.inflate(inflater,container,false)
        binding=fragmentBinding

        return fragmentBinding.root
    }
    fun navigate()
    {
        findNavController().navigate(R.id.action_profileDetailsFragment_to_cardsFragment)
    }


}
