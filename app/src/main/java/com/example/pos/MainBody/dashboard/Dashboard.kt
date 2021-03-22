package com.example.pos.MainBody.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pos.*
import com.example.pos.MainBody.dashboard.ordersActivites.ActiveOrderActivity
import com.example.pos.MainBody.registration.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.*

class Dashboard : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        Log.i("", "OnCreate: Dashboard.\n") /* Printing onCreate() to Logcat. */

        val userId = intent.getStringExtra("userID") /* Retrieving the username passed from Login/Register. */
        val emailId = intent.getStringExtra("nameID") /* Retrieving the email passed from Login/Register. */

        userDetails.text = "User ID :: $userId" /* Setting the User ID to be the text of a TextView. */
        userDetails.text = "Email ID :: $emailId" /* Setting the Email ID to be the text of a TextView. */

        garysImageView.setOnClickListener {
            val intent = Intent(this@Dashboard, Dashboard::class.java)  /* Creating an Intent to go to Dashboard. */
            startActivity(intent) /* Starting Activity. */
        }

        customersButton.setOnClickListener {
            val intent = Intent(this@Dashboard, CustomerActivity::class.java)  /* Creating an Intent to go to CustomerActivity. */
            startActivity(intent)                /* Starting Activity. - Shows the Database containing customers. */
        }

        placeOrderButton.setOnClickListener {
            val intent = Intent(this@Dashboard, OrderActivity::class.java)  /* Creating an Intent to go to OrderActivity. */
            startActivity(intent) /* Starting Activity. - Shows the screen for making orders. */
        }

        ordersButton.setOnClickListener {
            val intent = Intent(this@Dashboard, ActiveOrderActivity::class.java)  /* Creating an Intent to go to ActiveOrderActivity. */
            startActivity(intent) /* Starting Activity. - Shows orders that have been created. */
        }

        managementButton.setOnClickListener {
            val intent = Intent(this@Dashboard, ManagementActivity::class.java)  /* Creating an Intent to go to ManagementActivity. */
            startActivity(intent) /* Starting Activity. - Shows the options for viewing sales and logout. */
        }
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
        Log.i("", "OnResume: Dashboard.\n")
    }

    /* Printing onPause() to Logcat. */
    override fun onPause() {
        super.onPause()
        Log.i("", "OnPause: Dashboard.\n")
    }

    /* Printing onStop() to Logcat. */
    override fun onStop() {
        super.onStop()
        Log.i("", "OnStop: Dashboard.\n")
    }

    /* Printing onDestroy() to Logcat. */
    override fun onDestroy() {
        super.onDestroy()
        Log.i("", "OnDestroy: Dashboard.\n")
    }

    /* Printing onRestart() to Logcat. */
    override fun onRestart() {
        super.onRestart()
        Log.i("", "OnRestart: Dashboard.\n")
    }
} /* Ending class. */