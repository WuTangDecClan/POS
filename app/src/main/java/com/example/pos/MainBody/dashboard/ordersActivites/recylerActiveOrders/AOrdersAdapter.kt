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

class AOrdersAdapter(private var aorderList: List<ActiveOrderModel> ) : RecyclerView.Adapter<AOrdersAdapter.AOrderViewHolder>() {

    class AOrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView = view.nameView
        val addressView = view.address1View
        val mobileView = view.mobileView
        val eircodeView = view.eircodeView
        val paymentView = view.paymentView
        val paymentAmountView = view.paymentAmountView
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

    fun updateList(list: List<ActiveOrderModel> ) {
        this.aorderList = list
        notifyDataSetChanged()
    }
}