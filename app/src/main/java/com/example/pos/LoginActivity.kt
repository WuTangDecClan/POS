package com.example.pos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.i("Splash Screen", "OnCreate: Splash Screen.\n") /* Printing onCreate() to Logcat. */

        /* If the User presses the Register Account Button. */

        registerButton.setOnClickListener {
            onBackPressed() /* Stops Activities of LoginActivity being created. */
            val intent = Intent(this, RegisterActivity::class.java) /* Creating new intent. */
            startActivity(intent) /* Starting the new Activity. */
        }

        /* If the User presses the Login Account Button. */

        buttonLogin.setOnClickListener {
            when {  /* Switch Statement. */
                TextUtils.isEmpty(loginUser.text.toString().trim { it <= ' ' }) -> { /* Trimming the input of spaces and if empty printing this toast. */
                    Toast.makeText(this@LoginActivity, "Please enter email.", Toast.LENGTH_SHORT).show() /* Printing short Toast to the screen. */
                    loginUser.setHint("Fill this field!")
                }

                TextUtils.isEmpty(loginPassword.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(this@LoginActivity, "Please enter password.", Toast.LENGTH_SHORT).show()
                    loginPassword.setHint("Fill this field!")
                }
                else -> {

                    val email: String = loginUser.text.toString().trim { it <= ' ' }
                    val password: String = loginPassword.text.toString().trim { it <= ' ' }

                    // Create an instance and create a register a user with email and password.
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                //if the registration is sucessfully done
                                if (task.isSuccessful) {

                                    Toast.makeText(this@LoginActivity, "you are registered.", Toast.LENGTH_SHORT).show()

                                    val intent = Intent(this@LoginActivity, Dashboard::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(this@LoginActivity, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                                }
                            }
                }
            }
        }
    }
}
