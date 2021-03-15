package com.example.pos.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.R
import com.example.pos.model.Customer
import kotlinx.android.synthetic.main.custom_row.view.*

/* Adapters provide a binding from an app-specific data set to views that are displayed within a RecyclerView. */

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var customerList = emptyList<Customer>() /*Declaring the List of type Customer that will hold customers. */

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {} /* A ViewHolder describes an item view and metadata about its place within the RecyclerView. */

    /* When the ViewHolder is created return custom_row.xml which is the View used to display elements in the RecyclerView */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    /* Getting the count on the number of elements in customerList and returning it. */
    override fun getItemCount(): Int {
        return customerList.size
    }

    /* In OnCreateVH() the views are created and the ViewHolder contains reference to them. Here, the specific data is assigned to the Views */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentCustomer = customerList[position] /* Getting the current Customer and storing it in currentCustomer. */
        holder.itemView.nameView.text = currentCustomer.name /* Updating the name View with customer's name. */
        holder.itemView.address1View.text = currentCustomer.address /* Updating the address View with customer's address. */
        holder.itemView.eircodeView.text = currentCustomer.eircode /* Updating the eircode View with customer's eircode. */
        holder.itemView.mobileView.text = currentCustomer.mobile /* Updating the mobile View with customer's number. */

        holder.itemView.rowConstraintLayout.setOnClickListener { /* If a customer is clicked on in the List Fragment. */
            val action = listFragmentDirections.actionListFragmentToUpdateFragment(currentCustomer) /* This class is automatically generated for our list fragment. Can pass our customer object to our update class. */
            holder.itemView.findNavController().navigate(action) /* Changing to the update fragment and using the Current Customer to fill in the info. */
        }
    }
    /* Finishing assigning views and information to customer, now inserting into customerList and notifying the RecyclerView of the change.*/
    fun setData(customer: List<Customer>) {
        this.customerList = customer
        notifyDataSetChanged()
    }
} /* Ending List Adapter. */