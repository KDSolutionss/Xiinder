package com.example.xiinder
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.xiinder.databinding.CardItemBinding
import com.example.xiinder.network.CardInfo
import com.example.xiinder.network.ProfileInfo

class CardsAdapter(val context: Context, private val profiles: Map<Int, ProfileInfo>, private val dataset: Map<Int, CardInfo>, val goToProfileFragment: (id: Int) -> Unit,
                   val goToCardDetailsFragment: (id: Int) -> Unit):RecyclerView.Adapter<CardsAdapter.ViewHolder>() {
    inner class ViewHolder(private var binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cardInfo: CardInfo) {
            binding.Title.text = cardInfo.title
            binding.image.setImageResource(cardInfo.imageId)

            binding.userName.text = profiles[cardInfo.userId]?.name ?: "Имя"
            binding.Title.setOnClickListener{goToCardDetailsFragment(cardInfo.cardId)}
            binding.linearLayoutForImage.setOnClickListener{goToCardDetailsFragment(cardInfo.cardId)}
            binding.userName.setOnClickListener{goToProfileFragment(cardInfo.userId)}
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CardItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataset[position]
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}