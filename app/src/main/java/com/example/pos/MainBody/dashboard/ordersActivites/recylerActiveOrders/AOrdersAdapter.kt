package com.example.pos.MainBody.dashboard.ordersActivites.recylerActiveOrders

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.pos.R
import com.example.pos.model.ActiveOrderModel
import kotlinx.android.synthetic.main.active_order_item.view.*

/* Adapters provide a binding from an app-specific data set to views that are displayed within a RecyclerView. */
class AOrdersAdapter(private var aorderList: List<ActiveOrderModel>) :
/* Data Model:
customerName, customerNumber, customerPostal, customerAddress, paymentAmount, paymentType*/

    RecyclerView.Adapter<AOrdersAdapter.AOrderViewHolder>() {

    /* This method is called right when the adapter is created and is used to initialize the ViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AOrderViewHolder {
        /* When the View Holder is created the layout to be used is active_order_item.xml, returned from the function. */
        val view = LayoutInflater.from(parent.context).inflate(R.layout.active_order_item, parent, false)
        return AOrderViewHolder(view)
    }

    /* Returns the number of items in the List Array. */
    override fun getItemCount(): Int {
        return this.aorderList.size
    }

    /* Ties all the data submitted to the correct fields specified by the layout type. */
    override fun onBindViewHolder(holder: AOrderViewHolder, position: Int) {
        val currentItem = this.aorderList[position]

        holder.nameView.text = currentItem.customerName
        holder.addressView.text = currentItem.customerNumber
        holder.mobileView.text = currentItem.customerPostal
        holder.eircodeView.text = currentItem.customerAddress

        holder.paymentAmountView.text = currentItem.paymentAmount
        holder.paymentView.text = currentItem.paymentType
    }

    /* Function that lets the Adapter know there has been a new entry added. */
    fun updateList(list: List<ActiveOrderModel> ) {
        this.aorderList = list
        notifyDataSetChanged()
    }

    /* View Holder that gets the views from active_order_item.xml and stores them in variables. */
    class AOrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView = view.nameView!!
        val addressView = view.address1View!!
        val mobileView = view.mobileView!!
        val eircodeView = view.eircodeView!!
        val paymentView = view.paymentView!!
        val paymentAmountView = view.paymentAmountView!!
    }
}