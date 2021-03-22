package com.example.multiplerecyclerview

import android.content.Context
import android.content.Intent
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

/* Adapters provide a binding from an app-specific data set to views that are displayed within a RecyclerView. */
class OrderAdapter(val context: Context, val items: ArrayList<DataModel>) : /* Carries over the context of the RecyclerView & an Array List that is Data Model Type. */
/* Data Model:
itemName, itemQuantity, itemPrice, topping1, topping2, topping3, topping4, delivery, sideName, sidePrice, openFoodDetails, openFoodCharge, customerName, customerNumber, customerPostal, customerAddress paymentAmount, paymentType, viewType */

    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    /* Companion objects are objects whose properties and functions are tied to a class but not to the instance of that class. */
    companion object {
        const val NO_TOPPING = 1 /* If 1 is returned then the item to be added to the RecyclerView is a Pizza with no toppings. */
        const val TOPPINGS_4 = 4 /* If 4 is returned then the item to be added to the RecyclerView is a Pizza with toppings. */
        const val DELIVERY_CHARGE = 9 /* If 9 is returned then the item to be added to the RecyclerView is a delivery charge. */
        const val OPEN_FOOD_CHARGE = 12 /* If 12 is returned then the item to be added to the RecyclerView is an Open Food Charge. */
        const val SIDE_ORDER_TYPE_1 = 13 /* If 13 is returned then the item to be added to the RecyclerView is a Side Item with no toppings. */
        const val SIDE_ORDER_TYPE_2 = 14 /* If 14 is returned then the item to be added to the RecyclerView is a Side Item with toppings. */
        const val CUSTOMER = 21 /* If 21 is returned then the item to be added to the RecyclerView is a Customer. */
    }

    /* This method is called right when the adapter is created and is used to initialize the ViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        /* WHEN defines a conditional expression with multiple branches. It is similar to the switch statement in C-like languages. */
        return when (viewType) { /* Based on What type has been specified the correct item layout is returned with the correct views for holding data. */
            NO_TOPPING -> ViewHolder(LayoutInflater.from(context).inflate(R.layout.food_item, parent, false))
            TOPPINGS_4 -> ViewHolder4(LayoutInflater.from(context).inflate(R.layout.food_item_4, parent, false))
            DELIVERY_CHARGE -> ViewHolder2(LayoutInflater.from(context).inflate(R.layout.delivery_charge, parent, false))
            SIDE_ORDER_TYPE_1 -> ViewHolder5(LayoutInflater.from(context).inflate(R.layout.sideorder_type1, parent, false))
            SIDE_ORDER_TYPE_2 -> ViewHolder6(LayoutInflater.from(context).inflate(R.layout.sideorder_type2, parent, false))
            OPEN_FOOD_CHARGE -> ViewHolder3(LayoutInflater.from(context).inflate(R.layout.openfood_charge, parent, false))
            CUSTOMER -> ViewHolder7(LayoutInflater.from(context).inflate(R.layout.customer_item, parent, false))
            else -> ViewHolder(LayoutInflater.from(context).inflate(R.layout.food_item, parent, false))
        }
    } /*End onCreate. */

    /* Ties all the data submitted to the correct fields specified by the layout type. */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position] /* Getting the position in the Data Model List Array and storing in item. */

        when (holder) { /* When the holder is ... */
            is ViewHolder -> { /* Pizza with no toppings/information. */
                holder.productQuantity.text = item.itemQuantity
                holder.productName.text = item.itemName
                holder.productPrice.text = item.itemPrice
            }
            is ViewHolder2 -> { /* Delivery Charge. */
                holder.deliveryPrice.text = item.delivery
            }
            is ViewHolder3 -> { /* Open Food Charge. */
                holder.openFoodDetails.text = item.openFoodDetails
                holder.openFoodPrice.text = item.openFoodCharge
            }
            is ViewHolder4 -> { /* Pizza with toppings/information. */
                holder.productQuantity4.text = item.itemQuantity
                holder.productName4.text = item.itemName
                holder.productPrice4.text = item.itemPrice

                holder.topping1.text = item.topping1
                holder.topping2.text = item.topping2
                holder.topping3.text = item.topping3
                holder.topping4.text = item.topping4
            }
            is ViewHolder5 -> { /* Side Order with no extra information/toppings. */
                holder.sideName.text = item.sideName
                holder.sidePrice.text = item.sidePrice
            }
            is ViewHolder6 -> { /* Side Order with extra information/toppings. */
                holder.sideName2.text = item.sideName
                holder.sidePrice2.text = item.sidePrice

                holder.toppingside1.text = item.topping1
                holder.toppingside2.text = item.topping2
            }
            is ViewHolder7 -> { /* Customer information. */
                holder.customer.text = item.customerName
                holder.number.text = item.customerNumber
                holder.eircode.text = item.customerPostal
                holder.address.text = item.customerAddress
            }
        }
    } /* End onBind. */

    /* Returns the Item View Type. */
    override fun getItemViewType(position: Int): Int {
        return items[position].viewType
    }

    /* Returns the number of items in the List Array. */
    override fun getItemCount(): Int {
        return this.items.size
    }

    /* Pizza with no toppings/information. */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val productQuantity = view.productQuantityView!!
        val productName = view.productNameView!!
        val productPrice = view.productPriceView!!

        init { /* Acts like a constructor in Java. */
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

        }
    }

    /* Delivery Charge. */
    class ViewHolder2(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var deliveryPrice = view.deliveryPriceView!!
        val intent: Intent? = null
        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

        }
    }

    /* Open Food Charge. */
    class ViewHolder3(view: View) : RecyclerView.ViewHolder(view) {
        var openFoodDetails = view.openFoodView!!
        var openFoodPrice = view.openFoodPriceView!!
    }

    /* Pizza with toppings/information. */
    class ViewHolder4(view: View) : RecyclerView.ViewHolder(view) {
        val productQuantity4 = view.productQuantityView4!!
        val productName4 = view.productNameView4!!
        val productPrice4 = view.productPriceView4!!

        val topping1 = view.topping1View4!!
        val topping2 = view.topping2View4!!
        val topping3 = view.topping3View4!!
        val topping4 = view.topping4View4!!
    }

    /* Side Order with no extra information/toppings. */
    class ViewHolder5(view: View) : RecyclerView.ViewHolder(view) {
        val sideName = view.sideNameView!!
        val sidePrice = view.sidePriceView!!
    }

    /* Side Order with extra information/toppings. */
    class ViewHolder6(view: View) : RecyclerView.ViewHolder(view) {
        val sideName2 = view.sideNameView2!!
        val sidePrice2 = view.sidePriceView2!!

        val toppingside1 = view.topping1View2!!
        val toppingside2 = view.topping2View2!!

    }

    /* Customer information. */
    class ViewHolder7(view: View) : RecyclerView.ViewHolder(view) {
        val customer = view.nameView!!
        val number = view.mobileView!!
        val eircode = view.eircodeView!!
        val address = view.address1View!!
    }
}