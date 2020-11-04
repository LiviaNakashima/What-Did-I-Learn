package br.com.wcc.whatdidilearn.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.wcc.whatdidilearn.R
import br.com.wcc.whatdidilearn.entities.ItemLearned

class LearnedItemsAdapter: RecyclerView.Adapter<LearnedItemsAdapter.LearnedItemViewHolder>() {
    var data = listOf<ItemLearned>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    inner class LearnedItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleItem: TextView = itemView.findViewById(R.id.tv_itemTitle)
        val descriptionItem: TextView = itemView.findViewById(R.id.tv_itemDescription)
        val levelItem: View = itemView.findViewById(R.id.ll_itemLevel)

        fun bind(title: String, description: String, color: Int){
            titleItem.text = title
            descriptionItem.text = description
            levelItem.setBackgroundResource(color)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearnedItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_learned, parent, false)
        return LearnedItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: LearnedItemViewHolder, position: Int) {
        val itemLernedToShow: ItemLearned = data.get(position)
        holder.bind(itemLernedToShow.title, itemLernedToShow.description, itemLernedToShow.understandingLevel.color)

    }
}