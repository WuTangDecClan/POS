package com.example.pos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.foodmodels.FoodItem
import kotlinx.android.synthetic.main.food_item.view.*
import kotlinx.android.synthetic.main.food_item.view.productNameView
import kotlinx.android.synthetic.main.food_item.view.productPriceView
import kotlinx.android.synthetic.main.food_item.view.productQuantityView
import kotlinx.android.synthetic.main.food_item_4.view.*
import org.w3c.dom.Text

class FoodAdapter(val foodList: List<FoodItem>, val listener: onItemClickListener) : /* : indicates inheritance. Two arguments are a list of food items and an onItemClickListener. */
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {    /* Inherits from RecyclerView Adapter. */

/* Takes the data we want in a recyclerview and adapts it for a recyclerview. */
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder { /* Called as soon as the ViewHolder is created. */

        return FoodViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.food_item, /* Overriding and saying "Please use this layout". */
            parent,
            false)
        )
    }
    /* Get the number of items in the list. */
    override fun getItemCount() = foodList.size

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) { /* Passes the view holder and the position of the object. */

        val currentItem = foodList[position]

        if(holder is FoodViewHolder) {
            holder.productQuantity.text = currentItem.productQuantity
            holder.productName.text = currentItem.productName
            holder.productCost.text = currentItem.productCost
        } else if (holder is FoodViewHolder) {
            holder.productQuantity.text = currentItem.productQuantity
            holder.productName.text = currentItem.productName
            holder.productCost.text = currentItem.productCost
        }
    }

    inner class FoodViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val productQuantity: TextView = itemView.productQuantityView4
        val productName: TextView = itemView.productNameView4
        val productCost: TextView = itemView.productPriceView4
        val additional1: TextView = itemView.topping1View
        val additional2: TextView = itemView.topping2View
        val additional3: TextView = itemView.topping3View
        val additional4: TextView = itemView.topping4View

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
}