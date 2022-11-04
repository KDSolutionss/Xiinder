package com.example.xiinder.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.xiinder.DataForProfiles
import com.example.xiinder.R
import com.example.xiinder.databinding.FragmentProfileBinding
import com.example.xiinder.network.CardInfo

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters


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
            binding.UserInfo.text = currentProfile.info
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