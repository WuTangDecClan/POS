package com.example.pos.MainBody.dashboard.ordersActivites

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.pos.R
import com.example.pos.model.Customer
import com.example.pos.viewmodel.POSViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_customer_quick.add_btn
import kotlinx.android.synthetic.main.activity_customer_quick.editTextName
import kotlinx.android.synthetic.main.activity_customer_quick.editTextNumber
import kotlinx.android.synthetic.main.activity_customer_quick.editTextPostalAddress
import kotlinx.android.synthetic.main.activity_customer_quick.editTextTextAddress

const val CUSTOMER_CHOICE = 23

/* Customer Quick Activity is an activity that is for quickly adding a customer to an order. */
class CustomerQuickActivity : AppCompatActivity() {

    private lateinit var _posViewModel: POSViewModel /* Storing the View Model in variable posViewModel. */

    /* Access a Cloud Firestore instance from the Activity. */
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("", "OnCreate: Customer Quick Activity.\n")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_quick)

        /* Creates ViewModelProvider. This will create ViewModels and retain them in a store of the given ViewModelStoreOwner. */
        _posViewModel = ViewModelProvider(this).get(POSViewModel::class.java)

        /* If the user clicks on the add button. */
        add_btn.setOnClickListener {
            insertDataToDatabase()  /* Method to insert the data into the database is called. */
        }
    }
    private fun insertDataToDatabase() { /* Getting the texts and adding them to the Customer Database */
        val name = editTextName.text.toString() /* Getting the ediText Name, converting to a String and storing it as name. */
        val address = editTextTextAddress.text.toString() /* Getting the ediText Address, converting to a String and storing it as address. */
        val eircode = editTextPostalAddress.text.toString() /* Getting the ediText eircode, converting to a String and storing it as eircode. */
        val mobile = editTextNumber.text.toString() /* Getting the ediText mobile, converting to a String and storing it as mobile. */

        /* Creating an intent that when called will go to OrderActivity.kt. */
        val intent = Intent()  /* Creating an Intent. */
        intent.putExtra("customerName", name) /* Adding name as extra data. */
        intent.putExtra("customerNumber", mobile) /* Adding number as extra data. */
        intent.putExtra("postalCode", eircode) /* Adding eircode as extra data. */
        intent.putExtra("address", address) /* Adding address as extra data. */

        /* Creating a Customer Object. Using all the stored variables containing user imputed information and storing in val Customer an instance of Customer Object. */
        val customer = Customer(0, name, address, eircode, mobile)
        /* Adding Customer to the Room Database. */
        _posViewModel.addCustomer(customer)

        /* Creating a new Customer.  */
        val cust = hashMapOf("name" to name, "number" to mobile, "eircode" to eircode, "address" to address)

        /* Add a new document with a generated ID. */
        db.collection("customers").add(cust).addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }
        setResult(CUSTOMER_CHOICE, intent) /* Setting the Result to pass the OK (-1) Result and including the intent with its data. */
        finish() /* Ending the  Activity. */
    }

    /* Printing onStart() to Logcat. */
    override fun onStart() {
        super.onStart()
        Log.i("", "OnStart: Customer Quick Activity.\n")
    }

    /* Printing onResume() to Logcat. */
    override fun onResume() {
        super.onResume()
        Log.i("", "OnResume: Customer Quick Activity.\n")
    }

    /* Printing onPause() to Logcat. */
    override fun onPause() {
        super.onPause()
        Log.i("", "OnPause: Customer Quick Activity.\n")
    }

    /* Printing onStop() to Logcat. */
    override fun onStop() {
        super.onStop()
        Log.i("", "OnStop: Customer Quick Activity.\n")
    }

    /* Printing onDestroy() to Logcat. */
    override fun onDestroy() {
        super.onDestroy()
        Log.i("", "OnDestroy: Customer Quick Activity.\n")
    }

} /* Ending Activity. */
