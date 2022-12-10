package com.example.xiinder.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xiinder.R
import com.example.xiinder.CardsAdapter
import com.example.xiinder.DataSetForCards
import com.example.xiinder.StoreToken
import com.example.xiinder.databinding.FragmentCardsBinding

lateinit var binding: FragmentCardsBinding

class CardsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardsBinding.inflate(inflater)

        binding.photosLinear.adapter= this.context?.let { CardsAdapter(it,DataSetForCards().loadCards(), ::goToProfileFragment) }

        return binding.root
    }

    fun goToProfileFragment(profileId: Int) {
        val action = CardsFragmentDirections.actionCardsFragmentToProfileFragment(profileId)
        findNavController().navigate(action)
    }

}