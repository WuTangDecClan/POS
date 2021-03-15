package com.example.pos.MainBody.dashboard.ordersActivites.recylerActiveOrders

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.pos.R
import com.example.pos.model.ActiveOrderModel
import kotlinx.android.synthetic.main.active_order_item.view.*

class AOrdersAdapter(private val aorderList: List<ActiveOrderModel> ) : RecyclerView.Adapter<AOrdersAdapter.AOrderViewHolder>() {

    class AOrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.nameView
        val addressView: TextView = view.address1View
        val mobileView: TextView = view.mobileView
        val eircodeView: TextView = view.eircodeView
        val paymentView: TextView = view.paymentView
        val paymentAmountView: TextView = view.paymentAmountView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AOrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.active_order_item, parent, false)

        return AOrderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return aorderList.size
    }

    override fun onBindViewHolder(holder: AOrderViewHolder, position: Int) {
        val currentItem = aorderList[position]

        holder.nameView.text = currentItem.customerName
        holder.addressView.text = currentItem.customerNumber
        holder.mobileView.text = currentItem.customerPostal
        holder.eircodeView.text = currentItem.customerAddress

        holder.paymentAmountView.text = currentItem.paymentAmount
        holder.paymentView.text = currentItem.paymentType
    }
}