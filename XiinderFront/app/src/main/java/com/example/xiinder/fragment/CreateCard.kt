package com.example.xiinder.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.xiinder.Card
import com.example.xiinder.R
import com.example.xiinder.SharedViewModel
import com.example.xiinder.databinding.FragmentCreateCardBinding
import io.ktor.client.request.*
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.*
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [CreateCard.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateCard : Fragment() {
    private val PICK_IMAGE=100
    private var imageUri: Uri? = null
    var binding: FragmentCreateCardBinding?=null
    private val viewModel: SharedViewModel by activityViewModels()
    // TODO: Rename and change types of parameters




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding=FragmentCreateCardBinding.inflate(inflater,container,false)
        binding=fragmentBinding
        binding!!.button2.setOnClickListener {
            val title=binding!!.editTitle.text
            val info=binding!!.editInfo.text
            val contact=binding!!.editContacts.text
            val image=binding!!.imageView.drawable
            if (title.isNotEmpty()&&info.isNotEmpty()&&contact.isNotEmpty()&&image!=null)
            {lifecycleScope.launch { apply(title.toString(),info.toString(),contact.toString(),"dataMock")}}
             }
        binding!!.uploadImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE) }
        return fragmentBinding.root
    }
    suspend fun apply(title:String, info:String, contacts:String, image:String)
    {
        val card=Card(title,info,contacts,image)
        val message = viewModel.client.post("http://192.168.0.104:8888/add_card"){ // or your data class
            contentType(ContentType.Application.Json)
            setBody(card)
        }
        findNavController().navigate(R.id.action_createCard_to_cardsFragment)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data?.data
            binding?.imageView?.setImageURI(imageUri)
        }
    }
}