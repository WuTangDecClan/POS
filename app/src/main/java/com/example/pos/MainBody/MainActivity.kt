package com.example.pos.MainBody

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.pos.MainBody.dashboard.Dashboard
import com.example.pos.R

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* Window Manager hides the action bar from the top of the screen. */

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        Log.i("Splash Screen", "OnCreate: Splash Screen.\n") /* Printing onCreate() to Logcat. */

        /* Declaring Variables to store Splash Screen Logo & Text. Also storing the animation. */

        val shopLogo = findViewById<ImageView>(R.id.garryLogo) /* Logo. */
        val splashScreenText = findViewById<TextView>(R.id.spalsh_screen_text) /* Splash Screen Text. */
        val splashAnimation = AnimationUtils.loadAnimation(this,
            R.anim.splash_anim
        ) /* Loading splash_anim.xml. */

        /* Calling the animation on the Logo & on the text. */
        shopLogo.animation = splashAnimation /* Logo appears and moves down the screen. */
        splashScreenText.animation = splashAnimation /* Splash Screen text appears and moves down the screen. */

        /* Calling the new Activity. */
        Handler().postDelayed({
                val intent = Intent(this, Dashboard::class.java)
            startActivity(intent) /* Starting the new Activity. */
            finish() /* Preventing the user from returning to the Splash Screen. */
        },1000.toLong()) /* The Splash Screen Appears for five seconds. */
    } /* Ending onCreate. */

    /* Printing onStart() to Logcat. */
    override fun onStart() {
        super.onStart()
        Log.i("", "OnStart: Splash Screen.\n")
    }

    /* Printing onRestoreInstanceState() to Logcat. */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("", "OnRestoreInstanceState: Splash Screen.\n")
    }

    /* Printing onResume() to Logcat. */
    override fun onResume() {
        super.onResume()
        Log.i("", "OnResume: Splash Screen.\n")
    }

    /* Printing onPause() to Logcat. */
    override fun onPause() {
        super.onPause()
        Log.i("", "OnPause: Splash Screen.\n")
    }

    /* Printing onStop() to Logcat. */
    override fun onStop() {
        super.onStop()
        Log.i("", "OnStop: Splash Screen.\n")
    }

    /* Printing onDestroy() to Logcat. */
    override fun onDestroy() {
        super.onDestroy()
        Log.i("", "OnDestroy: Splash Screen.\n")
    }

    /* Printing onRestart() to Logcat. */
    override fun onRestart() {
        super.onRestart()
        Log.i("", "OnRestart: Splash Screen.\n")
    }
} /* Ending class. */