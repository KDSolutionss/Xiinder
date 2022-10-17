package com.example.xiinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xiinder.R
import com.example.xiinder.databinding.FragmentCardsBinding

var binding:FragmentCardsBinding?=null
class CardsFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCardsBinding.inflate(inflater)
        val divider = DividerItemDecoration(context,DividerItemDecoration.VERTICAL)
        divider.setDrawable(context?.let { ContextCompat.getDrawable(it,R.drawable.divider_decoration) }!!)
        binding.photosLinear.addItemDecoration(divider)
        binding.photosLinear
        binding.photosLinear.adapter= this.context?.let { CardsAdapter(it,DataSetForCards().loadCards()) }
        return binding.root
    }

}