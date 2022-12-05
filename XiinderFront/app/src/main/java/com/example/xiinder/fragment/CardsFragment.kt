package com.example.xiinder.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.xiinder.CardsAdapter
import com.example.xiinder.DataForProfiles
import com.example.xiinder.DataSetForCards
import com.example.xiinder.databinding.FragmentCardsBinding

lateinit var binding: FragmentCardsBinding

class CardsFragment : Fragment() {
    // TODO: Rename and change types of parameters

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