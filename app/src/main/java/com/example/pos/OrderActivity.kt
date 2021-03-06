package com.example.pos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.multiplerecyclerview.OrderAdapter
import kotlinx.android.synthetic.main.activity_order.*

const val INDEX = 0
const val FILE_ID = 1

class OrderActivity : AppCompatActivity() {

    val list = ArrayList<DataModel>() /*ArrayList that is type Data Model. */

    /* Adapter class is initialized and list is passed in the param. */
    val orderAdapter = OrderAdapter(this, getItemsList())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        customerButton.setOnClickListener { /* Customer onclick button: Gets taken to the customer screen. */
            val intent = Intent(this@OrderActivity, CustomerActivity::class.java)  /* Creating an Intent to go to Customer Activity. */
            startActivity(intent) /* Starting Activity. */
        }

        margButton.setOnClickListener { /* Margherita Button onClick. */
            val price = 11.90
            Toast.makeText(this, "Margerita clicked", Toast.LENGTH_SHORT).show()
            var intent = Intent(this@OrderActivity, PizzaActivity::class.java)  /* Creating an Intent to go to Pizza Activity. */
            intent.putExtra("itemName", "Margerita") /* Adding the foodName to the intent. */
            intent.putExtra("itemPrice", "11.9") /* Adding the foodPrice to the intent. */
            startActivityForResult(intent,1) /* Starting Activity for result. */
        }

        /* Set the LayoutManager that this RecyclerView will use. */
        orderRecyclerView.layoutManager = LinearLayoutManager(this)

        /* Adapter instance is set to the recyclerview to inflate the items. */
        orderRecyclerView.adapter = orderAdapter

    }

    /* This function is invoked once we return from Pizza Activity. */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.i("Order", "OnActivityResult.\n") /* Adding Logging information. */

        if(resultCode == RESULT_OK) { /* If the result is -1 "Okay". */
            /* Calling the insertItem function to add a View to the RecyclerView. */
            val itemName:String = data?.getStringExtra("itemName").toString() /* storing the values from arguments returned. */
            val itemPrice:String = data?.getStringExtra("itemPrice").toString() /* storing the values from arguments returned. */
            val itemSize:String = data?.getStringExtra("itemSize").toString() /* storing the values from arguments returned. */
            /* Calling the insertItem function to add a View to the RecyclerView. */
            insertItem(name = itemName, price = itemPrice, size = itemSize)
        } else {
            Log.i("Order", "onActivityResult failed\n") /* Logging the failed intent retreat. */
        }
    }

    /* This function is invoked to insert Items into the RecyclerView. */
    fun insertItem(name: String, price: String, size: String) {

        Toast.makeText(this, "Item added!", Toast.LENGTH_SHORT).show() /* Toast Message to confirm insertion. */

        val newItem = DataModel("$name", "$size", "$price", viewType = OrderAdapter.NO_TOPPING) /* Adding the item with correct arguments */
        list.add(INDEX, newItem) /* Adding Item at Position Index. */
        orderAdapter.notifyItemInserted(INDEX) /* Notifying the Adapter of the addition. */
    }

    /* This function is invoked to insert Items with Extras into the RecyclerView. */
    fun insertItemExtras(name: String, price: String, size: String, top1: String, top2: String, top3: String, top4: String) {

        Toast.makeText(this, "Item added!", Toast.LENGTH_SHORT).show() /* Toast Message to confirm insertion. */

        val newItem = DataModel("$name", "$size", "$price", "$top1", "$top2", "$top3", "$top4", viewType = OrderAdapter.TOPPINGS_4) /* Adding the item with correct arguments */
        list.add(INDEX, newItem) /* Adding Item at Position Index. */
        orderAdapter.notifyItemInserted(INDEX) /* Notifying the Adapter of the addition. */
    }

    private fun getItemsList(): ArrayList<DataModel> {

        //list.add(DataModel("Romana","1","12.50", "Pepperoni", "Aubergine", "Ex Mozz.", "Salami", OrderAdapter.TOPPINGS_4))
        //list.add(DataModel("American","1","12.50", viewType = OrderAdapter.NO_TOPPING))

        return list
    }

}










/*
        customerButton.setOnClickListener {
            val intent = Intent(this@OrderActivity, CustomerActivity::class.java)  /* Creating an Intent to go to Dashboard. */
            startActivity(intent) /* Starting Activity. */
        }

        margButton.setOnClickListener {
            val food = "Margerita"
            val price = 11.90
            Toast.makeText(this, "$food clicked", Toast.LENGTH_LONG).show()
            insertItem(view = margButton, foodType = "Margherita", price = price)
        }

        fun removeItem(view: View) {
        val index = Random.nextInt(8)

        foodList.removeAt(index)
        adapter.notifyItemRemoved(index)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_LONG).show()
        val clickedItem = foodList[position]
        clickedItem.productQuantity = "2"
        adapter.notifyItemChanged(position)
    }
 */
