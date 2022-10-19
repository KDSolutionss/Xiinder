package com.example.xiinder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.xiinder.CardsAdapter
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

        binding.photosLinear.adapter =
            this.context?.let { CardsAdapter(it, DataSetForCards().loadCards()) }
        return binding.root
    }

}