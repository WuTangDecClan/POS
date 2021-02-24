package com.example.pos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.food_item.view.*

class FoodAdapter(private val foodList: List<FoodItem>,  private val listener: onItemClickListener) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val productQuantity: TextView = itemView.productQuantityView
        val productName: TextView = itemView.productNameView
        val productCost: TextView = itemView.productPriceView

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)

        return FoodViewHolder(itemView)
    }

    override fun getItemCount() = foodList.size

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentItem = foodList[position]

        holder.productQuantity.text = currentItem.productQuantity
        holder.productName.text = currentItem.productName
        holder.productCost.text = currentItem.productCost
    }
}