package com.example.pos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.*

class Dashboard : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val userId = intent.getStringExtra("userID")
        val emailId = intent.getStringExtra("nameID")

        userDetails.text = "User ID :: $userId"
        userDetails.text = "$emailId"

        userID.setOnClickListener {
            //Logout from app.
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this@Dashboard, LoginActivity::class.java))
            finish()
        }

        customersButton.setOnClickListener {
            val intent = Intent(this@Dashboard, CustomerActivity::class.java)  /* Creating an Intent to go to Dashboard. */
            startActivity(intent) /* Starting Activity. */

        }

        placeOrderButton.setOnClickListener {
            val intent = Intent(this@Dashboard, OrderActivity::class.java)  /* Creating an Intent to go to Dashboard. */
            startActivity(intent) /* Starting Activity. */

        }

        ordersButton.setOnClickListener {
            val intent = Intent(this@Dashboard, LoginActivity::class.java)  /* Creating an Intent to go to Dashboard. */
            startActivity(intent) /* Starting Activity. */
        }
    }

}