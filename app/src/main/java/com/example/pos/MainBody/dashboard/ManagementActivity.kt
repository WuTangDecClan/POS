package com.example.pos.MainBody.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pos.MainBody.registration.LoginActivity
import com.example.pos.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.garysImageView
import kotlinx.android.synthetic.main.activity_dashboard.userDetails
import kotlinx.android.synthetic.main.activity_management.*

class ManagementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_management)

        val userId = intent.getStringExtra("userID")
        val emailId = intent.getStringExtra("nameID")

        userDetails.text = "User ID :: $userId"
        userDetails.text = "$emailId"

        garysImageView.setOnClickListener {
            val intent = Intent(this@ManagementActivity, Dashboard::class.java)  /* Creating an Intent to go to Dashboard. */
            startActivity(intent) /* Starting Activity. */
        }

        salesButton.setOnClickListener {
            val intent = Intent(this@ManagementActivity, ViewSalesActivity::class.java)  /* Creating an Intent to go to Dashboard. */
            startActivity(intent) /* Starting Activity. */

        }

        endDayButton.setOnClickListener {
            val intent = Intent(this@ManagementActivity, Dashboard::class.java)  /* Creating an Intent to go to Dashboard. */
            startActivity(intent) /* Starting Activity. */

        }

        logoutButton.setOnClickListener {
            //Logout from app.
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this@ManagementActivity, LoginActivity::class.java))
            finish()
        }
    }
}