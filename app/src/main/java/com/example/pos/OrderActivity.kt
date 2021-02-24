package com.example.pos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_order.*
import kotlin.random.Random

class OrderActivity : AppCompatActivity(), FoodAdapter.onItemClickListener {

    private val foodList = generateDummyList(20)
    private val adapter = FoodAdapter(foodList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val foodList = generateDummyList(20)

        foodRecyclerView.adapter = adapter
        foodRecyclerView.layoutManager = LinearLayoutManager(this)
        foodRecyclerView.setHasFixedSize(true)
    }

    fun insertItem(view: View) {
        val index = Random.nextInt(8)

        val newItem = FoodItem("1","Vico","14.50")

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

    private fun generateDummyList(size: Int) : ArrayList<FoodItem> {

        val list = ArrayList<FoodItem>()

        for (i in 0 until size) {
            val item = FoodItem("1", "Killiney Hill", "14.50")
            list += item
        }

        return list
    }
}