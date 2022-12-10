package com.example.xiinder.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.xiinder.R
import com.example.xiinder.SharedViewModel
import com.example.xiinder.databinding.FragmentStartBinding
import kotlinx.coroutines.launch


class StartFragment : Fragment() {

    private var binding: FragmentStartBinding? = null
    private val viewModel: SharedViewModel by activityViewModels()
    fun goToNextScreen() {
        findNavController().navigate(R.id.action_startFragment_to_cardsFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        lifecycleScope.launch { binding!!.button.text=viewModel.getToken() }
        return fragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startFragment = this@StartFragment
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}