package com.example.xiinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.xiinder.R
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
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)
        //val cardsButton = rootView.findViewById<Button>(R.id.CardsButton)
        val userName = rootView.findViewById<TextView>(R.id.UserName)
        val userInfo = rootView.findViewById<TextView>(R.id.UserInfo)
        val image = rootView.findViewById<ImageView>(R.id.UserPhoto)
        val args: ProfileFragmentArgs by navArgs()
        val profileId = args.profileId
        val currentProfile = DataForProfiles().getProfilesInfo()[profileId]
        if (currentProfile != null) {
            userInfo.text = currentProfile.info
        }
        if (currentProfile != null) {
            image.setImageResource(currentProfile.imageId)
        }
        if (currentProfile != null) {
            userName.setText(currentProfile.name)
        }
        return rootView
    }
}