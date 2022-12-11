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
import com.example.xiinder.*
import com.example.xiinder.databinding.FragmentCardsBinding

lateinit var binding: FragmentCardsBinding

class CardsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardsBinding.inflate(inflater)

        binding.photosLinear.adapter= this.context?.let { CardsAdapter(it,
            DataForProfiles().getProfilesInfo(), DataSetForCards().loadCards(),
            ::goToProfileFragment, ::goToCardDetailsFragment) }

        return binding.root
    }

    private fun goToProfileFragment(profileId: Int) {
        val action = CardsFragmentDirections.actionCardsFragmentToProfileFragment(profileId)
        findNavController().navigate(action)
    }

    private fun goToCardDetailsFragment(cardId: Int){
        val action = CardsFragmentDirections.actionCardsFragmentToCardDetailsFragment(cardId)
        findNavController().navigate(action)
    }

}