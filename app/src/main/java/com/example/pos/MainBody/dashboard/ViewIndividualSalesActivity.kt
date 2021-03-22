package com.example.pos.MainBody.dashboard

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pos.MainBody.dashboard.ordersActivites.recylerActiveOrders.AOrdersAdapter
import com.example.pos.R
import com.example.pos.model.ActiveOrderModel
import com.example.pos.model.SalesModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.activity_view_individual_sales.*
import kotlinx.android.synthetic.main.activity_view_individual_sales.itemsButton
import kotlinx.android.synthetic.main.activity_view_individual_sales.printButton
import kotlinx.android.synthetic.main.activity_view_sales.*
import java.util.*
import kotlin.collections.ArrayList

@Suppress("NAME_SHADOWING")
class ViewIndividualSalesActivity : AppCompatActivity() {

    private val salesList = ArrayList<SalesModel>()
    private val adapter = SalesAdapter(salesList)

    /* Access a Cloud Firestore instance from the Activity. */
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("", "OnCreate: ActiveOrderActivity.\n")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_individual_sales)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val c = Calendar.getInstance()

        val year = c.get(Calendar.YEAR)
        val lastYear = c.get(Calendar.YEAR) - 1
        val month = c.get(Calendar.MONTH) + 1
        val lastMonth = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        db.collection("Food Sales - $day.$month.$year")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val itemName = document.getField<String>("Item Name")
                    val itemSales = document.getField<Int>("Sales Number")
                    val itemID = document.id
                    var monthSales: Int = 0
                    var yearSales: Int = 0
                    db.collection("Food Sales - $day.$lastMonth.$year").get().addOnSuccessListener { result ->
                        for (document in result) {
                            if (document.id == itemID) {
                                monthSales = document.getField<Int>("Sales Number")!!
                            }
                        }
                        db.collection("Food Sales - $day.$month.$lastYear").get().addOnSuccessListener { result ->
                            for (document in result) {
                                if (document.id == itemID) {
                                    yearSales = document.getField<Int>("Sales Number")!!
                                }
                            }
                        }
                        val newItem = SalesModel(itemName = itemName, todaySales = itemSales, monthSales = monthSales, yearSales = yearSales)
                        salesList.add(INDEX, newItem)
                        adapter.updateList(salesList)
                        adapter.notifyItemInserted(INDEX)
                    }
                }
            }

        /* If the print button is clicked then show a builder. */
        printButton.setOnClickListener {
            val builder = AlertDialog.Builder(this) /* Initialize a new instance of Builder. */
            builder.setTitle("Complete Order") /* Set the alert dialog title. */
            builder.setMessage("Are you sure you want to print all the Individual Sales?") /* Display a message on alert dialog. */
            // Set a positive button and its click listener on alert dialog
            builder.setPositiveButton("Yes") { _: DialogInterface, _: Int -> /* Set a positive button and its click listener on alert dialog. */
                Toast.makeText(this, "Printing Item Sales...", Toast.LENGTH_LONG).show()
            }
            builder.setNegativeButton("No") { _: DialogInterface, _: Int -> /* Display a negative button on alert dialog. */
                Toast.makeText(this, "Printing Cancelled...", Toast.LENGTH_LONG).show()
            }
            builder.show() /* Showing the builder when clicked. */
        }

        /* If the Items Button is clicked then go to View Individual Sales Activity. */
        itemsButton.setOnClickListener {
            val intent = Intent(this@ViewIndividualSalesActivity, ViewSalesActivity::class.java)  /* Creating an Intent to go to Dashboard. */
            startActivity(intent) /* Starting Activity. */
        }
    }
}