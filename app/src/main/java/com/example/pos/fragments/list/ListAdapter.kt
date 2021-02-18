package com.example.pos.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.R
import com.example.pos.listFragmentDirections
import com.example.pos.model.Customer
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var customerList = emptyList<Customer>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun getItemCount(): Int {
        return customerList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = customerList[position]
        holder.itemView.nameView.text = currentItem.name
        holder.itemView.address1View.text = currentItem.address
        holder.itemView.eircodeView.text = currentItem.eircode
        holder.itemView.mobileView.text = currentItem.mobile.toString()

        holder.itemView.rowConstraintLayout.setOnClickListener {
            val action = listFragmentDirections.actionListFragmentToUpdateFragment(currentItem) /* This class is automatically generated for our list fragment. Can pass our customer object to our update class. */
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(customer: List<Customer>) {
        this.customerList = customer
        notifyDataSetChanged()
    }
}