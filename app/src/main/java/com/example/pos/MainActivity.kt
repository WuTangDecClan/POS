package com.example.pos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* Window Manager hides the action bar from the top of the screen. */

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        /* Declaring Variables to store Splash Screen Logo & Text. Also storing the animation. */

        val shopLogo = findViewById<ImageView>(R.id.garryLogo) /* Logo. */
        val splashScreenText = findViewById<TextView>(R.id.spalsh_screen_text) /* Splash Screen Text. */
        val splashAnimation = AnimationUtils.loadAnimation(this,R.anim.splash_anim) /* Loading splash_anim.xml. */

        /* Calling the animation on the Logo & on the text. */
        shopLogo.setAnimation(splashAnimation) /* Logo appears and moves down the screen. */
        splashScreenText.setAnimation(splashAnimation) /* Splash Screen text appears and moves down the screen. */

        /* Calling the new Activity. */
        Handler().postDelayed({
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent) /* Starting the new Activity. */
            finish() /* Preventing the user from returning to the Splash Screen. */
        }, 5000.toLong()) /* The Splash Screen Appears for five seconds. */
    }
}