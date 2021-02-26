package com.example.pos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pos.foodmodels.FoodItem
import kotlinx.android.synthetic.main.activity_order.*
import kotlin.random.Random

class OrderActivity : AppCompatActivity(), FoodAdapter.onItemClickListener {

    private var orderList: List<OrderModel> = ArrayList()


    private val foodList = ArrayList<FoodItem>() /* Initiliazing foodlist an array of type FoodItem. */
    private val adapter = FoodAdapter(foodList, this) /* Adapter class is initialized and list is passed in the param. */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        foodRecyclerView.adapter = adapter /* adapter instance is set to the recyclerview to inflate the items. */
        foodRecyclerView.layoutManager = LinearLayoutManager(this) /* Set the layoutmanager that this recyclerview will use. */
        foodRecyclerView.setHasFixedSize(true)

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
    }

    private fun getItemsList(): ArrayList<String> {
        val list = ArrayList<String>()

        for(i in 1..15) {
            list.add("Item $i")
        }

        return list
    }

    fun insertItem(view: View, foodType: String, price: Double) {
        val index = 0
        Toast.makeText(this, "Margerita clicked", Toast.LENGTH_LONG).show()
        val newItem =
            FoodItem("1", "$foodType", "$price")
        foodList.add(index, newItem)
        adapter.notifyItemInserted(index)
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

    private fun loadOrderData() {

    }
}