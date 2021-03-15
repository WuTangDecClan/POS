package com.example.multiplerecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.model.DataModel
import com.example.pos.R
import kotlinx.android.synthetic.main.openfood_charge.view.*
import kotlinx.android.synthetic.main.food_item.view.*
import kotlinx.android.synthetic.main.food_item_4.view.*
import kotlinx.android.synthetic.main.delivery_charge.view.*
import kotlinx.android.synthetic.main.sideorder_type1.view.*
import kotlinx.android.synthetic.main.sideorder_type2.view.*
import kotlinx.android.synthetic.main.customer_item.view.*

class OrderAdapter(val context: Context, val items: ArrayList<DataModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val NO_TOPPING = 1
        const val TOPPINGS_4 = 4
        const val DELIVERY_CHARGE = 9
        const val OPEN_FOOD_CHARGE = 12
        const val SIDE_ORDER_TYPE_1 = 13
        const val SIDE_ORDER_TYPE_2 = 14
        const val CUSTOMER = 21
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
        } else if (viewType == DELIVERY_CHARGE) {
            return ViewHolder2(
                LayoutInflater.from(context).inflate(
                    R.layout.delivery_charge,
                    parent,
                    false
                )
            )
        } else if (viewType == SIDE_ORDER_TYPE_1) {
        return ViewHolder5(
            LayoutInflater.from(context).inflate(
                R.layout.sideorder_type1,
                parent,
                false
            )
        )
    } else if (viewType == SIDE_ORDER_TYPE_2) {
        return ViewHolder6(
            LayoutInflater.from(context).inflate(
                R.layout.sideorder_type2,
                parent,
                false
            )
        )
    } else if (viewType == OPEN_FOOD_CHARGE) {
            return ViewHolder3(
                LayoutInflater.from(context).inflate(
                    R.layout.openfood_charge,
                    parent,
                    false
                )
            )
        } else if (viewType == CUSTOMER) {
            return ViewHolder7(
                LayoutInflater.from(context).inflate(
                    R.layout.customer_item,
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

        }else if(holder is ViewHolder2) {

            holder.deliveryPrice.text = item.delivery

        } else if(holder is ViewHolder3) {
            holder.openFoodDetails.text = item.openFoodDetails
            holder.openFoodPrice.text = item.openFoodCharge
        } else if(holder is ViewHolder4) {

            holder.productQuantity4.text = item.itemQuantity
            holder.productName4.text = item.itemName
            holder.productPrice4.text = item.itemPrice

            holder.topping1.text = item.topping1
            holder.topping2.text = item.topping2
            holder.topping3.text = item.topping3
            holder.topping4.text = item.topping4

        } else if(holder is ViewHolder5) {

            holder.sideName.text = item.sideName
            holder.sidePrice.text = item.sidePrice

        } else if(holder is ViewHolder6) {
            holder.sideName2.text = item.sideName
            holder.sidePrice2.text = item.sidePrice

            holder.toppingside1.text = item.topping1
            holder.toppingside2.text = item.topping2
        } else if(holder is ViewHolder7) {
            holder.customer.text = item.customerName
            holder.number.text = item.customerNumber
            holder.eircode.text = item.customerPostal
            holder.address.text = item.customerAddress
        }

    }

    override fun getItemViewType(position: Int): Int {
        return items[position].viewType
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val productQuantity = view.productQuantityView
        val productName = view.productNameView
        val productPrice = view.productPriceView

        init { /* Acts like a constructor in Java. */
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }
    }

    interface onItemClickListener {
        fun  onItemClick()
    }

    class ViewHolder2(view: View) : RecyclerView.ViewHolder(view) {
        var deliveryPrice = view.deliveryPriceView
    }

    class ViewHolder3(view: View) : RecyclerView.ViewHolder(view) {
        var openFoodDetails = view.openFoodView
        var openFoodPrice = view.openFoodPriceView
    }

    class ViewHolder4(view: View) : RecyclerView.ViewHolder(view) {
        val productQuantity4 = view.productQuantityView4
        val productName4 = view.productNameView4
        val productPrice4 = view.productPriceView4

        val topping1 = view.topping1View4
        val topping2 = view.topping2View4
        val topping3 = view.topping3View4
        val topping4 = view.topping4View4
    }

    class ViewHolder5(view: View) : RecyclerView.ViewHolder(view) {
        val sideName = view.sideNameView
        val sidePrice = view.sidePriceView
    }

    class ViewHolder6(view: View) : RecyclerView.ViewHolder(view) {
        val sideName2 = view.sideNameView2
        val sidePrice2 = view.sidePriceView2

        val toppingside1 = view.topping1View2
        val toppingside2 = view.topping2View2

    }

    class ViewHolder7(view: View) : RecyclerView.ViewHolder(view) {
        val customer = view.nameView
        val number = view.mobileView
        val eircode = view.eircodeView
        val address = view.address1View

    }
}