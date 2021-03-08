package com.example.pos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.multiplerecyclerview.OrderAdapter
import kotlinx.android.synthetic.main.activity_open_food.*
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.activity_order.view.*
import kotlin.properties.Delegates

const val INDEX = 0

class OrderActivity : AppCompatActivity() {

    val list = ArrayList<DataModel>() /*ArrayList that is type Data Model. */

    /* Adapter class is initialized and list is passed in the param. */
    val orderAdapter = OrderAdapter(this, getItemsList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        customerButton.setOnClickListener { /* Customer onclick button: Gets taken to the customer screen. */
            val intent = Intent(this@OrderActivity, CustomerActivity::class.java)  /* Creating an Intent to go to Customer Activity. */
            startActivityForResult(intent,1) /* Starting Activity for result. */
        }

        deliveryChargeButton.setOnClickListener { /* Delivery onclick button: Gets taken to the customer screen. */
            val intent = Intent(this@OrderActivity, DeliveryActivity::class.java)  /* Creating an Intent to go to Customer Activity. */
            startActivityForResult(intent,1) /* Starting Activity for result. */
        }

        openFoodButton.setOnClickListener {  /* OpenFood onclick button: Gets taken to the customer screen. */
            val intent = Intent(this@OrderActivity, OpenFoodActivity::class.java)  /* Creating an Intent to go to Customer Activity. */
            startActivityForResult(intent,1) /* Starting Activity for result. */
        }

        cancelOrderButton.setOnClickListener {/* cancelOrder onclick button: Return to the Dashboard, finish the activity.. */
            val intent = Intent(this@OrderActivity, Dashboard::class.java)  /* Creating an Intent to go to Customer Activity. */
            startActivity(intent) /* Starting Activity. */
            finish() /* Finishing the Activity. */
        }

        /* Set the LayoutManager that this RecyclerView will use. */
        orderRecyclerView.layoutManager = LinearLayoutManager(this)

        /* Adapter instance is set to the recyclerview to inflate the items. */
        orderRecyclerView.adapter = orderAdapter

    }

    /* This function is invoked once we return from Pizza Activity. */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var temp = textViewPrice.text.toString().toDouble()

        Log.i("Order", "OnActivityResult.\n") /* Adding Logging information. */

        if (resultCode == RESULT_DELIVERY) {
            val charge: String = data?.getStringExtra("deliveryCharge").toString() /* storing the values from arguments returned. */

            temp += charge.toDouble()
            temp = String.format("%.2f", temp).toDouble()
            textViewPrice.text = temp.toString()

            insertCharge(charge = charge)
        }

        if (resultCode == RESULT_CODE_1 || resultCode == RESULT_CODE_2) { /* If the result is -1 "Okay". */

            /* Calling the insertItem function to add a View to the RecyclerView. */
            val itemName: String = data?.getStringExtra("itemName").toString() /* storing the values from arguments returned. */
            val itemPrice: String = data?.getStringExtra("itemPrice").toString() /* storing the values from arguments returned. */
            val itemSize: String = data?.getStringExtra("itemSize").toString() /* storing the values from arguments returned. */
            val top1: String = data?.getStringExtra("topping1").toString() /* storing the values from arguments returned. */
            val top2: String = data?.getStringExtra("topping2").toString() /* storing the values from arguments returned. */
            val top3: String = data?.getStringExtra("topping3").toString() /* storing the values from arguments returned. */
            val top4: String = data?.getStringExtra("topping4").toString() /* storing the values from arguments returned. */

            temp += itemPrice.toDouble()
            temp = String.format("%.2f", temp).toDouble()
            textViewPrice.text = temp.toString()

            if (resultCode == RESULT_CODE_1)
                insertItem(name = itemName, price = itemPrice, size = itemSize)
            else if (resultCode == RESULT_CODE_2)
                insertItem4(name = itemName, price = itemPrice, size = itemSize, top1 = top1, top2 = top2, top3 = top3, top4 = top4)
        }

        if (resultCode == SIDE_CODE_1 || resultCode == SIDE_CODE_2) {
            /* Calling the insertItem function to add a View to the RecyclerView. */
            val itemName: String = data?.getStringExtra("itemName").toString() /* storing the values from arguments returned. */
            val itemPrice: String = data?.getStringExtra("itemPrice").toString()
            val top1: String = data?.getStringExtra("topping1").toString() /* storing the values from arguments returned. */
            val top2: String = data?.getStringExtra("topping2").toString()

            temp += itemPrice.toDouble()
            temp = String.format("%.2f", temp).toDouble()
            textViewPrice.text = temp.toString()

            if (resultCode == SIDE_CODE_1)
                insertSide(name = itemName, price = itemPrice)
            else if (resultCode == SIDE_CODE_2)
                insertSide2(name = itemName, price = itemPrice, top1 = top1, top2 = top2)
        }

        if (resultCode == RESULT_OPEN_FOOD) {

            val openFoodDetails: String = data?.getStringExtra("openFoodDetails").toString() /* storing the values from arguments returned. */
            val openFoodPrice: String = data?.getStringExtra("openFoodPrice").toString() /* storing the values from arguments returned. */

            temp += openFoodPrice.toDouble()
            temp = String.format("%.2f", temp).toDouble()
            textViewPrice.text = temp.toString()

            insertOpenFood(openFoodDetails = openFoodDetails, openFoodPrice = openFoodPrice)
        }
    }

    private fun insertSide(name: String, price: String) {

        Toast.makeText(this, "Side Item Added!", Toast.LENGTH_SHORT).show() /* Toast Message to confirm insertion. */

        val newItem = DataModel(sidePrice = "$price", sideName = "$name", viewType = OrderAdapter.SIDE_ORDER_TYPE_1) /* Adding the item with correct arguments */
        list.add(INDEX, newItem) /* Adding Item at Position Index. */
        orderAdapter.notifyItemInserted(INDEX) /* Notifying the Adapter of the addition. */
    }

    private fun insertSide2(name: String, price: String, top1: String, top2: String) {

        Toast.makeText(this, "Side Item Added!", Toast.LENGTH_SHORT).show() /* Toast Message to confirm insertion. */

        val newItem = DataModel(sidePrice = "$price", sideName = "$name", topping1 = top1, topping2 = top2, viewType = OrderAdapter.SIDE_ORDER_TYPE_2) /* Adding the item with correct arguments */
        list.add(INDEX, newItem) /* Adding Item at Position Index. */
        orderAdapter.notifyItemInserted(INDEX) /* Notifying the Adapter of the addition. */
    }

    private fun insertCharge(charge: String) {

        Toast.makeText(this, "Delivery Charge Added!", Toast.LENGTH_SHORT).show() /* Toast Message to confirm insertion. */

        val newItem = DataModel(delivery ="$charge", viewType = OrderAdapter.DELIVERY_CHARGE) /* Adding the item with correct arguments */
        list.add(INDEX, newItem) /* Adding Item at Position Index. */
        orderAdapter.notifyItemInserted(INDEX) /* Notifying the Adapter of the addition. */
    }

    /* This function is invoked to insert Items into the RecyclerView. */
    fun insertItem(name: String, price: String, size: String) {

        Toast.makeText(this, "Item added!", Toast.LENGTH_SHORT).show() /* Toast Message to confirm insertion. */

        val newItem = DataModel("$name", "$size", "$price", viewType = OrderAdapter.NO_TOPPING) /* Adding the item with correct arguments */
        list.add(INDEX, newItem) /* Adding Item at Position Index. */
        orderAdapter.notifyItemInserted(INDEX) /* Notifying the Adapter of the addition. */
    }

    /* This function is invoked to insert Items with Extras into the RecyclerView. */
    fun insertItem4(name: String, price: String, size: String, top1: String, top2: String, top3: String, top4: String) {

        Toast.makeText(this, "Item added!", Toast.LENGTH_SHORT).show() /* Toast Message to confirm insertion. */

        val newItem = DataModel("$name", "$size", "$price", "$top1", "$top2", "$top3", "$top4", viewType = OrderAdapter.TOPPINGS_4) /* Adding the item with correct arguments */
        list.add(INDEX, newItem) /* Adding Item at Position Index. */
        orderAdapter.notifyItemInserted(INDEX) /* Notifying the Adapter of the addition. */
    }

    private fun insertOpenFood(openFoodDetails: String, openFoodPrice: String) {

        Toast.makeText(this, "Open Food Added!", Toast.LENGTH_SHORT).show() /* Toast Message to confirm insertion. */

        val newItem = DataModel(openFoodDetails = "$openFoodDetails", openFoodCharge = "$openFoodPrice",viewType = OrderAdapter.OPEN_FOOD_CHARGE) /* Adding the item with correct arguments */
        list.add(INDEX, newItem) /* Adding Item at Position Index. */
        orderAdapter.notifyItemInserted(INDEX) /* Notifying the Adapter of the addition. */
    }

    private fun getItemsList(): ArrayList<DataModel> {

        //list.add(DataModel("Romana","1","12.50", "Pepperoni", "Aubergine", "Ex Mozz.", "Salami", OrderAdapter.TOPPINGS_4))
        //list.add(DataModel("American","1","12.50", viewType = OrderAdapter.NO_TOPPING))

        return list
    }

    fun pizzaButton(view: View) {
        val buttonView : Button = view as Button
        val texty = buttonView.text.toString()
        val price = buttonView.tag.toString()
        Toast.makeText(this, "$texty clicked", Toast.LENGTH_SHORT).show()
        var intent = Intent(this@OrderActivity, PizzaActivity::class.java)  /* Creating an Intent to go to Pizza Activity. */
        intent.putExtra("itemName", "$texty") /* Adding the foodName to the intent. */
        intent.putExtra("itemPrice", "$price") /* Adding the foodPrice to the intent. */
        startActivityForResult(intent,1) /* Starting Activity for result. */
    }

    fun sideButton(view: View) {
        val buttonView : Button = view as Button
        val texty = buttonView.text.toString()
        val price = buttonView.tag.toString()
        Toast.makeText(this, "$texty $price clicked", Toast.LENGTH_SHORT).show()
        var intent = Intent(this@OrderActivity, SideActivity::class.java)  /* Creating an Intent to go to Pizza Activity. */
        intent.putExtra("itemName", "$texty") /* Adding the foodName to the intent. */
        intent.putExtra("itemPrice", "$price") /* Adding the foodPrice to the intent. */
        startActivityForResult(intent,1) /* Starting Activity for result. */
    }
}

