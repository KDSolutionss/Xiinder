package com.example.xiinder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.xiinder.DataForProfiles
import com.example.xiinder.databinding.FragmentProfileBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val args: ProfileFragmentArgs by navArgs()
        val profileId = args.profileId
        val currentProfile = DataForProfiles().getProfilesInfo()[profileId]
        val binding = FragmentProfileBinding.inflate(layoutInflater)
        val rootView = binding.root
        if (currentProfile != null) {
            binding.UserName.text = currentProfile.name
            binding.UserPhoto.setImageResource(currentProfile.imageId)
            binding.CardsButton.setOnClickListener{
                val action = CardsFragmentDirections.actionProfileFragmentToCardsFragment()
                findNavController().navigate(action)

            }
            binding.executePendingBindings()
        }
        return rootView
    }
}