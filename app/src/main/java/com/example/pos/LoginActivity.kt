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
        Log.i("Login", "OnCreate: Login.\n") /* Printing onCreate() to Logcat. */

        /* If the User presses the Register Account Button. */

        registerButton.setOnClickListener {
            onBackPressed() /* Stops Activities of LoginActivity being created. */
            val intent = Intent(this, RegisterActivity::class.java) /* Creating new intent. */
            startActivity(intent) /* Starting the new Activity. */
        } /* End registerButton onClickListener. */

        /* If the User presses the Login Account Button. */

        buttonLogin.setOnClickListener {
            when {  /* Switch Statement. */
                TextUtils.isEmpty(loginUser.text.toString().trim { it <= ' ' }) -> { /* Trimming the input of spaces and if empty printing this toast. */
                    Toast.makeText(this@LoginActivity, "Please enter email.", Toast.LENGTH_SHORT).show() /* Printing short Toast to the screen. Prompting for information. */
                    loginUser.setHint("Fill this field!") /* Resetting the hint message for the empty user field. */
                } /* End isEmpty. */
                TextUtils.isEmpty(loginPassword.text.toString().trim { it <= ' ' }) -> { /* Trimming the input of spaces and if empty printing this toast. */
                    Toast.makeText(this@LoginActivity, "Please enter password.", Toast.LENGTH_SHORT).show()  /* Printing short Toast to the screen. */
                    loginPassword.setHint("Fill this field!") /* Resetting the hint message for the empty password field. */
                } else -> { /* If the user field and password fields are not empty. */

                    /* Initializing user and password string variables. */
                    val user: String = loginUser.text.toString().trim { it <= ' ' } /* Making sure there are not any spaces in the user entry. */
                    val password: String = loginPassword.text.toString().trim { it <= ' ' } /* Making sure there are not any spaces in the password entry. */

                    /* Creating an instance of the Firebase. */
                    /* Signing into the Firebase Authentication passing user and password as arguments. */
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(user, password).addOnCompleteListener { task -> /* If the Login is successful. */
                        if (task.isSuccessful) { /* Printing a Toast Message to the user to do with successful Login. */
                            Toast.makeText(this@LoginActivity, "Sign-in complete", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@LoginActivity, Dashboard::class.java) /* Creating an Intent to go to Dashboard. */
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK /* Prevents Activities being created multiple times. */
                            intent.putExtra("userID", FirebaseAuth.getInstance().currentUser!!.uid) /* Adding user_id to the intent being sent to Dashboard. */
                            intent.putExtra("nameID", user) /* Adding the user's name to the intent being sent to Dashboard. */
                            startActivity(intent) /* Starting Activity. */
                            finish() /* Finishing the activity. */
                        } else { /* Sending a Toast with whatever exception has been reported back from the Firebase. */
                            Toast.makeText(this@LoginActivity, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show() /* !! used if the exception thrown back is null. */
                        } /* End if task is successful. */
                    } /* End Firebase sign-in. */
                } /* End else. */
            } /* End when. */
        } /* End Button Login setOnClickListener. */
    } /* End on Create. */

    /* Printing onStart() to Logcat. */
    override fun onStart() {
        super.onStart()
        Log.i("Login", "OnStart: Login.\n")
    }

    /* Printing onRestoreInstanceState() to Logcat. */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("Login", "OnRestoreInstanceState: Login.\n")
    }

    /* Printing onResume() to Logcat. */
    override fun onResume() {
        super.onResume()
        Log.i("Login", "OnResume: Login.\n")
    }

    /* Printing onPause() to Logcat. */
    override fun onPause() {
        super.onPause()
        Log.i("Login", "OnPause: Login.\n")
    }

    /* Printing onStop() to Logcat. */
    override fun onStop() {
        super.onStop()
        Log.i("Login", "OnStop: Login.\n")
    }

    /* Printing onDestroy() to Logcat. */
    override fun onDestroy() {
        super.onDestroy()
        Log.i("Login", "OnDestroy: Login.\n")
    }

    /* Printing onRestart() to Logcat. */
    override fun onRestart() {
        super.onRestart()
        Log.i("Login", "OnRestart: Login.\n")
    }
} /* End class. */
