package com.example.pos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.multiplerecyclerview.OrderAdapter
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity() {

    val list = ArrayList<DataModel>()
    // Adapter class is initialized and list is passed in the param.
    val orderAdapter = OrderAdapter(this, getItemsList())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

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

        //Set the LayoutManager that this RecyclerView will use.
        orderRecyclerView.layoutManager = LinearLayoutManager(this)

        //adapter instance is set to the recyclerview to inflate the items.
        orderRecyclerView.adapter = orderAdapter

    }

    fun insertItem(view: View, foodType: String, price: Double) {
        val index = 0
        Toast.makeText(this, "Margerita clicked", Toast.LENGTH_LONG).show()
        val newItem = DataModel("$foodType", "1", "$price", viewType = OrderAdapter.NO_TOPPING)
        list.add(index, newItem)
        orderAdapter.notifyItemInserted(index)
    }

    private fun getItemsList(): ArrayList<DataModel> {

        list.add(DataModel("Romana","1","12.50", "Pepperoni", "Aubergine", "Ex Mozz.", "Salami", OrderAdapter.TOPPINGS_4))
        list.add(DataModel("American","1","12.50", viewType = OrderAdapter.NO_TOPPING))

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
