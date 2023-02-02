package com.example.xiinder.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.xiinder.*
import com.example.xiinder.databinding.FragmentCardsBinding
import com.example.xiinder.MainActivity
import com.example.xiinder.network.CardInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


lateinit var binding: FragmentCardsBinding

class CardsFragment : Fragment() {
    private val viewModel:SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCardsBinding.inflate(inflater)
        CoroutineScope(Dispatchers.Main).launch {  binding.photosLinear.adapter= context?.let { CardsAdapter(it,
            DataForProfiles().getProfilesInfo(), DataSetForCards().loadCards(viewModel),
            ::goToProfileFragment, ::goToCardDetailsFragment) }}

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.inflateMenu(R.menu.menu)

        binding.toolbar.setOnMenuItemClickListener {
            findNavController().navigate(R.id.action_cardsFragment_to_createCard);true;
        }
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