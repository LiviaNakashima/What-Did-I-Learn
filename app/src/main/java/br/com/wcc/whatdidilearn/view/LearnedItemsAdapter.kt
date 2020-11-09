package br.com.wcc.whatdidilearn.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.wcc.whatdidilearn.R
import br.com.wcc.whatdidilearn.entities.ItemLearned

class LearnedItemsAdapter: RecyclerView.Adapter<LearnedItemsAdapter.LearnedItemViewHolder>() {
    var data = mutableListOf<ItemLearned>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    var removedItem : ItemLearned ? = null

    inner class LearnedItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleItem: TextView = itemView.findViewById(R.id.tv_itemTitle)
        val descriptionItem: TextView = itemView.findViewById(R.id.tv_itemDescription)
        val levelItem: View = itemView.findViewById(R.id.ll_itemLevel)
        val buttonDelete: Button = itemView.findViewById(R.id.bt_delete)

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
        val itemLearnedToShow: ItemLearned = data.get(position)
        holder.bind(itemLearnedToShow.title, itemLearnedToShow.description, itemLearnedToShow.understandingLevel.color)

        holder.buttonDelete.setOnClickListener {
            deleteItem(itemLearnedToShow)
        }

    }
    fun deleteItem(item: ItemLearned) {
        data.remove(item)
        removedItem = item
        notifyDataSetChanged()
    }

    fun getRemoveItemPosition() : ItemLearned? {
        return removedItem;
    }
}