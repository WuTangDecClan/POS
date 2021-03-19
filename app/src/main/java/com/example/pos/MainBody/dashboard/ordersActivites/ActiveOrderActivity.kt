package com.example.pos.MainBody.dashboard.ordersActivites

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.multiplerecyclerview.OrderAdapter
import com.example.pos.MainBody.dashboard.Dashboard
import com.example.pos.MainBody.dashboard.INDEX
import com.example.pos.MainBody.dashboard.ordersActivites.recylerActiveOrders.AOrdersAdapter
import com.example.pos.R
import com.example.pos.model.ActiveOrderModel
import com.example.pos.model.DataModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_list.*
import java.util.*
import kotlin.collections.ArrayList

class ActiveOrderActivity : AppCompatActivity() {
    private val aorderList = ArrayList<ActiveOrderModel>()
    private val adapter = AOrdersAdapter(aorderList)

    val c = Calendar.getInstance()

    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)+1
    val day = c.get(Calendar.DAY_OF_MONTH)

    /* Access a Cloud Firestore instance from the Activity. */
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("", "OnCreate: ActiveOrderActivity.\n")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_order)

        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)

        db.collection("Orders - 19.3.2021")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.i("", "IN LOOP FOR DOCUMENT: ActiveOrderActivity.\n")
                    val customerName = document.getField<String>("customer Name")
                    Log.i("", "$customerName: ActiveOrderActivity.\n")
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

        floatingActionButton.setOnClickListener {
            val intent = Intent(this@ActiveOrderActivity, Dashboard ::class.java)  /* Creating an Intent to go to Customer Activity. */
            startActivityForResult(intent,1) /* Starting Activity for result. */
        }

    }

    private fun insertNewOrder(customerName: String, customerNumber: String, customerPostal: String, customerAddress: String, paymentType: String, paymentTotal: String) {

        val c = Calendar.getInstance()

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)+1
        val day = c.get(Calendar.DAY_OF_MONTH)

        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        val time = "$hour.$minute"

        /* Creating a new Order.  */
        val order = hashMapOf(
            "customer Name" to customerName,
            "customer Number" to customerNumber,
            "eircode" to customerPostal,
            "address" to customerAddress,
            "payment Total" to paymentTotal,
            "payment Amount" to paymentType,
            "Date of Order" to time
        )

        val custNam = customerName.replace(" ","")
        val custNum = customerNumber.replace(" ","")
        db.collection("Orders - 19.3.2021").document("$custNam$custNum").set(order)

        val newItem = ActiveOrderModel(customerName,customerNumber, customerPostal, customerAddress, paymentTotal, paymentType)

        aorderList.add(0, newItem) /* Adding Item at Position Index. */
        adapter.notifyItemInserted(0) /* Notifying the Adapter of the addition. */
    }


    /* Printing onStart() to Logcat. */
    override fun onStart() {
        super.onStart()
        Log.i("", "OnStart: Dashboard.\n")
    }

    /* Printing onRestoreInstanceState() to Logcat. */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("", "OnRestoreInstanceState: Dashboard.\n")
    }

    /* Printing onResume() to Logcat. */
    override fun onResume() {
        super.onResume()
        Log.i("", "OnResume: ActiveOrderActivity.\n")
    }

    /* Printing onPause() to Logcat. */
    override fun onPause() {
        super.onPause()
        Log.i("", "OnPause: ActiveOrderActivity.\n")
    }

    /* Printing onStop() to Logcat. */
    override fun onStop() {
        super.onStop()
        Log.i("", "OnStop: ActiveOrderActivity.\n")
    }

    /* Printing onDestroy() to Logcat. */
    override fun onDestroy() {
        super.onDestroy()
        Log.i("", "OnDestroy: ActiveOrderActivity.\n")
    }

    /* Printing onRestart() to Logcat. */
    override fun onRestart() {
        super.onRestart()
        Log.i("", "OnRestart: ActiveOrderActivity.\n")
    }
} /* Ending class. */