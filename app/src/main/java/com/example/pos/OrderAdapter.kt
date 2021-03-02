package com.example.multiplerecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.DataModel
import com.example.pos.R
import kotlinx.android.synthetic.main.food_item.view.*
import kotlinx.android.synthetic.main.food_item_4.view.*


class OrderAdapter(val context: Context, val items: ArrayList<DataModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val NO_TOPPING = 1
        const val TOPPINGS_4 = 4
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(viewType == NO_TOPPING) {
            return ViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.food_item,
                    parent,
                    false
                )
            )
        } else if (viewType == TOPPINGS_4) {
            return ViewHolder4(
                LayoutInflater.from(context).inflate(
                    R.layout.food_item_4,
                    parent,
                    false
                )
            )
        } else {
            return ViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.food_item,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items.get(position)

        if(holder is ViewHolder) {

            holder.productQuantity.text = item.itemQuantity
            holder.productName.text = item.itemName
            holder.productPrice.text = item.itemPrice

        } else if(holder is ViewHolder4) {

            holder.productQuantity4.text = item.itemQuantity
            holder.productName4.text = item.itemName
            holder.productPrice4.text = item.itemPrice

            holder.topping1.text = item.topping1
            holder.topping2.text = item.topping2
            holder.topping3.text = item.topping3
            holder.topping4.text = item.topping4
        }

    }

    override fun getItemViewType(position: Int): Int {
        return items[position].viewType
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productQuantity = view.productQuantityView
        val productName = view.productNameView
        val productPrice = view.productPriceView
    }

    class ViewHolder4(view: View) : RecyclerView.ViewHolder(view) {
        val productQuantity4 = view.productQuantityView4
        val productName4 = view.productNameView4
        val productPrice4 = view.productPriceView4

        val topping1 = view.topping1View
        val topping2 = view.topping2View
        val topping3 = view.topping3View
        val topping4 = view.topping4View
    }
}