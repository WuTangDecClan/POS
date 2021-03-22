package com.example.pos.MainBody.dashboard

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.pos.R
import com.example.pos.model.SalesModel
import kotlinx.android.synthetic.main.activity_individual_sale_item.view.*

class SalesAdapter(private var salesList: List<SalesModel>) : RecyclerView.Adapter<SalesAdapter.SalesViewHolder>() {

    class SalesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView = view.nameView
        val salesTodayView = view.salesTodayView
        val salesMonthView = view.salesMonthView
        val salesYearView = view.salesYearView
    }

    /* Called by the RecyclerView when it is time to create a new View Holder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_individual_sale_item, parent, false)

        return SalesViewHolder(view)
    }

    /* Gets and returns the size of the List. */
    override fun getItemCount(): Int {
        return salesList.size
    }

    /* Fills each item on screen with data. */
    override fun onBindViewHolder(holder: SalesViewHolder, position: Int) {
        val currentItem = salesList[position]

        holder.nameView.text = currentItem.itemName
        holder.salesTodayView.text = currentItem.todaySales.toString()
        holder.salesMonthView.text = currentItem.monthSales.toString()
        holder.salesYearView.text = currentItem.yearSales.toString()

    }

    fun updateList(list: List<SalesModel> ) {
        this.salesList = list
        notifyDataSetChanged()
    }
}