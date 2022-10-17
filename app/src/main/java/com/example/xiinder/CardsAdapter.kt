package com.example.xiinder
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.xiinder.databinding.CardItemBinding
import com.example.xiinder.network.CardInfo

class CardsAdapter(val context: Context,  private val dataset: List<CardInfo>):RecyclerView.Adapter<CardsAdapter.ViewHolder>() {
    class ViewHolder(private var binding:CardItemBinding):
        RecyclerView.ViewHolder(binding.root)
    {

        fun bind(cardInfo: CardInfo) {
            binding.cardLabel.text=cardInfo.description
            binding.imageView.setImageResource(cardInfo.imageId)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CardItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=dataset[position]
        holder.itemView.animation= AnimationUtils.loadAnimation(holder.itemView.context, R.anim.item_animation)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}