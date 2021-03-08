package com.example.pos

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import kotlinx.android.synthetic.main.activity_delivery.*
import kotlinx.android.synthetic.main.activity_open_food.*

const val RESULT_OPEN_FOOD = 12

class OpenFoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_food)


        /* If the user clicks premium button to charge for addition: check size and call function to changePrice. */
        completeOpenFoodBtn.setOnClickListener {
            val openFoodDesc: String = openFoodDetailsview.text.toString()
            val openFoodCost: String = openFoodMoneyview.text.toString()
            if (openFoodDesc == "") {
                Toast.makeText(this, "Fill out the details of the open food", Toast.LENGTH_SHORT)
                    .show() /* Toast Message to confirm insertion. */
            } else  if (openFoodCost == "") {
                Toast.makeText(this, "Fill out payment details", Toast.LENGTH_SHORT)
                    .show() /* Toast Message to confirm insertion. */
            } else {
                val intent = Intent()  /* Creating an Intent. */
                intent.putExtra("openFoodDetails", openFoodDesc) /* Adding changes made to be sent back and read for the new entry in the RecyclerView. */
                intent.putExtra("openFoodPrice", openFoodCost) /* Adding changes made to be sent back and read for the new entry in the RecyclerView. */
                setResult(RESULT_OPEN_FOOD, intent) /* Setting the Result to pass the OK (-1) Result and including the intent with its data. */
                finish() /* Ending the  Activity. */
            }
        }
    }

    private fun changeColor(chosenButton: AppCompatButton?) {
        twoButton.setTextColor(Color.WHITE)
        twoFiftyButton.setTextColor(Color.WHITE)
        threeButton.setTextColor(Color.WHITE)
        threeFiftyButton.setTextColor(Color.WHITE)
        chosenButton!!.setTextColor(Color.rgb(136,27,50))
    }
}