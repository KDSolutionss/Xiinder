package com.example.xiinder
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
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
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}