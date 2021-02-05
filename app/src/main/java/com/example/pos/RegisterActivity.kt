package com.example.pos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        Log.i("Register", "OnCreate: Register.\n") /* Printing onCreate() to Logcat. */

        /* If the User presses the Login Account Button. */

        loginButton.setOnClickListener {
            onBackPressed() /* Stops Activities of LoginActivity being created. */
            val intent = Intent(this, LoginActivity::class.java) /* Creating new intent. */
            startActivity(intent) /* Starting the new Activity. */
        } /* End loginButton onClickListener. */

        /* If the User presses the Register Account Button. */

        buttonRegister.setOnClickListener {
            when { /* Switch Statement. */
                TextUtils.isEmpty(registerUser.text.toString().trim { it <= ' ' }) -> { /* Trimming the input of spaces and if empty printing this toast. */
                    Toast.makeText(this@RegisterActivity, "Please enter email.", Toast.LENGTH_SHORT).show() /* Printing short Toast to the screen. Prompting for information. */
                    registerUser.hint = "Fill this field!" /* Resetting the hint message for the empty user field. */
                } /* End isEmpty. */
                TextUtils.isEmpty(registerPassword.text.toString().trim { it <= ' ' }) -> { /* Trimming the input of spaces and if empty printing this toast. */
                    Toast.makeText(this@RegisterActivity, "Please enter password.", Toast.LENGTH_SHORT).show()  /* Printing short Toast to the screen. */
                    loginPassword.hint = "Fill this field!" /* Resetting the hint message for the empty password field. */
                } else -> { /* If the user field and password fields are not empty. */

                    /* Initializing user and password string variables. */
                    val user: String = registerUser.text.toString().trim { it <= ' ' } /* Making sure there are not any spaces in the user entry. */
                    val password: String = registerPassword.text.toString().trim { it <= ' ' } /* Making sure there are not any spaces in the password entry. */

                    /* Creating an instance of the Firebase. */
                    /* Signing into the Firebase Authentication passing user and password as arguments. */
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(user, password).addOnCompleteListener(OnCompleteListener<AuthResult> { task -> /* If the Login is successful. */
                        if (task.isSuccessful) { /* Printing a Toast Message to the user to do with successful Login. */
                            Toast.makeText(this@RegisterActivity, "Registration complete", Toast.LENGTH_SHORT).show()
                            val firebaseUser: FirebaseUser = task.result!!.user!! /* Creating firebaseUser variable to hold the result sent back. */
                            val intent = Intent(this@RegisterActivity, Dashboard::class.java)  /* Creating an Intent to go to Dashboard. */
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK /* Prevents Activities being created multiple times. */
                            intent.putExtra("userID", firebaseUser.uid) /* Adding user_id to the intent being sent to Dashboard. */
                            intent.putExtra("nameID", user) /* Adding the user's name to the intent being sent to Dashboard. */
                            startActivity(intent) /* Starting Activity. */
                            finish() /* Finishing the activity. */
                        } else { /* Sending a Toast with whatever exception has been reported back from the Firebase. */
                            Toast.makeText(this@RegisterActivity, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show() /* !! used if the exception thrown back is null. */
                        } /* End if task is successful. */
                    }) /* End Firebase sign-up. */
                } /* End else. */
            } /* End when. */
        } /* End Button Register setOnClickListener. */
    } /* End on Create. */

    /* Printing onStart() to Logcat. */
    override fun onStart() {
        super.onStart()
        Log.i("Register", "OnStart: Register.\n")
    }

    /* Printing onRestoreInstanceState() to Logcat. */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("Register", "OnRestoreInstanceState: Register.\n")
    }

    /* Printing onResume() to Logcat. */
    override fun onResume() {
        super.onResume()
        Log.i("Register", "OnResume: Register.\n")
    }

    /* Printing onPause() to Logcat. */
    override fun onPause() {
        super.onPause()
        Log.i("Register", "OnPause: Register.\n")
    }

    /* Printing onStop() to Logcat. */
    override fun onStop() {
        super.onStop()
        Log.i("Register", "OnStop: Register.\n")
    }

    /* Printing onDestroy() to Logcat. */
    override fun onDestroy() {
        super.onDestroy()
        Log.i("Register", "OnDestroy: Register.\n")
    }

    /* Printing onRestart() to Logcat. */
    override fun onRestart() {
        super.onRestart()
        Log.i("Register", "OnRestart: Register.\n")
    }
} /* End class. */

