package com.example.pos.MainBody.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.ListFragment
import com.example.pos.MainBody.dashboard.ordersActivites.DeliveryActivity
import com.example.pos.R

class CustomerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)

        /* This Activity immediately runs off code in ListFragment.kt
           located in fragments folder and the list folder.
         */
    }

    /* Function to search the Database. */
    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"
    }

    /* Printing onStart() to Logcat. */
    override fun onStart() {
        super.onStart()
        Log.i("", "OnStart: Customer.\n")
    }

    /* Printing onRestoreInstanceState() to Logcat. */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("", "OnRestoreInstanceState: Customer.\n")
    }

    /* Printing onResume() to Logcat. */
    override fun onResume() {
        super.onResume()
        Log.i("", "OnResume: Customer.\n")
    }

    /* Printing onPause() to Logcat. */
    override fun onPause() {
        super.onPause()
        Log.i("", "OnPause: Customer.\n")
    }

    /* Printing onStop() to Logcat. */
    override fun onStop() {
        super.onStop()
        Log.i("", "OnStop: Customer.\n")
    }

    /* Printing onDestroy() to Logcat. */
    override fun onDestroy() {
        super.onDestroy()
        Log.i("", "OnDestroy: Customer.\n")
    }

    /* Printing onRestart() to Logcat. */
    override fun onRestart() {
        super.onRestart()
        Log.i("", "OnRestart: Customer.\n")
    }
} /* Ending class. */
