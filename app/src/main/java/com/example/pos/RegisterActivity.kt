package com.example.pos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        loginButton.setOnClickListener {
            onBackPressed() /* Stops Activities of LoginActivity being created. */
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent) /* Starting the new Activity. */
        }

        buttonRegister.setOnClickListener {
            when {
                TextUtils.isEmpty(registerUser.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(this@RegisterActivity, "Please enter email.", Toast.LENGTH_SHORT).show()
                }

                TextUtils.isEmpty(registerPassword.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(this@RegisterActivity, "Please enter password.", Toast.LENGTH_SHORT).show()
                }
                else -> {

                    val email: String = registerUser.text.toString().trim { it <= ' ' }
                    val password: String = registerPassword.text.toString().trim { it <= ' ' }

                    // Create an instance and create a register a user with email and password.
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
                                //if the registration is sucessfully done
                                if (task.isSuccessful) {
                                    //Firebase registered user
                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(this@RegisterActivity, "you are registered.", Toast.LENGTH_SHORT).show()

                                    val intent = Intent(this@RegisterActivity, Dashboard::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseUser.uid)
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(this@RegisterActivity, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                                }
                            })
                }
            }
        }
    }
}
