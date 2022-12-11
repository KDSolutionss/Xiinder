package com.example.xiinder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.xiinder.DataSetForCards
import com.example.xiinder.databinding.FragmentCardDetailsBinding

class CardDetailsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val args:  CardDetailsFragmentArgs by navArgs()
        val cardId = args.cardId
        val currentCard = DataSetForCards().loadCards()[cardId]
        val binding = FragmentCardDetailsBinding.inflate(layoutInflater)
        val rootView = binding.root
        if (currentCard != null) {
            binding.CardImage.setImageResource(currentCard.imageId)
            binding.CardInfo.text = currentCard.description
            binding.CardTitle.text = currentCard.title
            binding.executePendingBindings()
        }
        return rootView
    }
}