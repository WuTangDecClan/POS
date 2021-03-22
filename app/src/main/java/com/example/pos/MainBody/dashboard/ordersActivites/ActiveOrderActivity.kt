package com.example.pos.MainBody.dashboard.ordersActivites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pos.MainBody.dashboard.Dashboard
import com.example.pos.MainBody.dashboard.INDEX
import com.example.pos.MainBody.dashboard.ordersActivites.recylerActiveOrders.AOrdersAdapter
import com.example.pos.R
import com.example.pos.model.ActiveOrderModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_list.*
import java.util.*
import kotlin.collections.ArrayList

/* Active Order Activity is an activity that shows all the orders that have been made that day. */
class ActiveOrderActivity : AppCompatActivity() {
/* Active Order Model:
customerName, customerNumber, customerPostal, customerAddress paymentAmount, paymentType */

    private val aorderList = ArrayList<ActiveOrderModel>() /* Declaring an Array List of type ActiveOrderModel. */
    private val adapter = AOrdersAdapter(aorderList) /* Adapter class is initialized and list is passed in the param. */

    /* Using the Calender Library to get the time & date based on the time zone selected on the device. */
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)+1
    val day = c.get(Calendar.DAY_OF_MONTH)

    /* Access the Cloud Firestore instance from the Activity. */
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("", "OnCreate: Active Order Activity.\n")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_order)

        recyclerview.adapter = adapter /* Adapter instance is set to the recyclerview to inflate the items. */
        recyclerview.layoutManager = LinearLayoutManager(this) /* Set the LayoutManager that this RecyclerView will use. */
        recyclerview.setHasFixedSize(true) /* If the RecyclerView has a fixed width and height then this function call optimizes the RecyclerView. */

        /* Accessing the Database. */
        db.collection("Orders - 22.3.2021").get().addOnSuccessListener { result ->
                for (document in result) { /* Looping through the document and collecting all the orders made. */
                    val customerName = document.getField<String>("customer Name")
                    val customerNumber = document.getField<String>("customer Number")
                    val customerPostal = document.getField<String>("eircode")
                    val customerAddress = document.getField<String>("address")
                    val paymentAmount = document.getField<String>("payment Amount")
                    val paymentType =  document.getField<String>("payment Total")
                    val newItem = ActiveOrderModel(customerName = customerName,customerNumber = customerNumber,customerPostal = customerPostal,customerAddress = customerAddress,paymentAmount = paymentType,paymentType = paymentAmount)
                    aorderList.add(INDEX,newItem)
                    adapter.updateList(aorderList)
                    adapter.notifyItemInserted(INDEX)
                }
            }

        floatingActionButton.setOnClickListener { /* If the Main Menu Button is clicked then go back to the Main Men. */
            val intent = Intent(this@ActiveOrderActivity, Dashboard ::class.java)  /* Creating an Intent to go to Dashboard Activity. */
            startActivityForResult(intent,1) /* Starting Activity for result. */
        }

    }

    /* Printing onStart() to Logcat. */
    override fun onStart() {
        super.onStart()
        Log.i("", "OnStart: Active Order Activity.\n")
    }

    /* Printing onRestoreInstanceState() to Logcat. */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("", "OnRestoreInstanceState: Active Order Activity.\n")
    }

    /* Printing onResume() to Logcat. */
    override fun onResume() {
        super.onResume()
        Log.i("", "OnResume: Active Order Activity.\n")
    }

    /* Printing onPause() to Logcat. */
    override fun onPause() {
        super.onPause()
        Log.i("", "OnPause: Active Order Activity.\n")
    }

    /* Printing onStop() to Logcat. */
    override fun onStop() {
        super.onStop()
        Log.i("", "OnStop: Active Order Activity.\n")
    }

    /* Printing onDestroy() to Logcat. */
    override fun onDestroy() {
        super.onDestroy()
        Log.i("", "OnDestroy: Active Order Activity.\n")
    }

    /* Printing onRestart() to Logcat. */
    override fun onRestart() {
        super.onRestart()
        Log.i("", "OnRestart: Active Order Activity.\n")
    }
} /* Ending class. */