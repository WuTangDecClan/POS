package com.example.pos.MainBody.dashboard.ordersActivites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.model.OrderModel
import com.example.pos.R
import kotlinx.android.synthetic.main.food_item.view.*
import kotlinx.android.synthetic.main.food_item_4.view.*

private const val ORDER_TYPE_0: Int = 0
private const val ORDER_TYPE_1: Int = 1
private const val ORDER_TYPE_2: Int = 2
private const val ORDER_TYPE_3: Int = 3
private const val ORDER_TYPE_4: Int = 4

class OrderListAdapter(var orderListItems: List<OrderModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // View Holders for all types of items
    class Item0ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(orderModel: OrderModel) {
            itemView.productNameView.text = orderModel.productName
            itemView.productQuantityView.text = orderModel.productQuantity
            itemView.productPriceView.text = orderModel.productCost

        }
    }

    // View Holders for all types of items
    class Item1ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(orderModel: OrderModel) {

        }
    }

    // View Holders for all types of items
    class Item2ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(orderModel: OrderModel) {

        }
    }

    // View Holders for all types of items
    class Item3ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(orderModel: OrderModel) {

        }
    }

    // View Holders for all types of items
    class Item4ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(orderModel: OrderModel) {
            itemView.productNameView4.text = orderModel.productName
            itemView.productQuantityView4.text = orderModel.productQuantity
            itemView.productPriceView4.text = orderModel.productCost

            itemView.topping1View4.text = orderModel.additional1
            itemView.topping2View4.text = orderModel.additional2
            itemView.topping3View4.text = orderModel.additional3
            itemView.topping4View4.text = orderModel.additional4
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == ORDER_TYPE_0) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
            return Item0ViewHolder(
                view
            )
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item_4, parent, false)
            return Item4ViewHolder(
                view
            )
        }
    }

    override fun getItemCount(): Int {
        return orderListItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(orderListItems[position].additional1 == "") {
            ORDER_TYPE_0
        } else {
            ORDER_TYPE_1
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == ORDER_TYPE_0) {
            (holder as Item0ViewHolder).bind(orderListItems[position])
        } else if(getItemViewType(position) == ORDER_TYPE_1) {
            (holder as Item2ViewHolder).bind(orderListItems[position])
        } else {
            (holder as Item4ViewHolder).bind(orderListItems[position])
        }
    }
}
