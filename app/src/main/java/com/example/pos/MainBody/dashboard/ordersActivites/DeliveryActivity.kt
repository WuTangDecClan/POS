package com.example.pos.MainBody.dashboard.ordersActivites

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.pos.R
import kotlinx.android.synthetic.main.activity_delivery.*

const val RESULT_DELIVERY = 9

class DeliveryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)
        
        var charge: String = "0.00"

        /* If the user clicks premium button to charge for addition: check size and call function to changePrice. */
        twoButton.setOnClickListener {
            changeColor(twoButton)
            charge = "2.00"
        }

        /* If the user clicks premium button to charge for addition: check size and call function to changePrice. */
        twoFiftyButton.setOnClickListener {
            changeColor(twoFiftyButton)
            charge = "2.50"
        }

        /* If the user clicks premium button to charge for addition: check size and call function to changePrice. */
        threeButton.setOnClickListener {
            changeColor(threeButton)
            charge = "3.00"
        }

        /* If the user clicks premium button to charge for addition: check size and call function to changePrice. */
        threeFiftyButton.setOnClickListener {
            changeColor(threeFiftyButton)
            charge = "3.50"
        }

        /* If the user clicks premium button to charge for addition: check size and call function to changePrice. */
        completePizzaBtn.setOnClickListener {
            if (charge == "0.00")
                Toast.makeText(this, "Pick a Delivery Charge!", Toast.LENGTH_SHORT).show() /* Toast Message to confirm insertion. */
            else {
                val intent = Intent()  /* Creating an Intent. */
                intent.putExtra("deliveryCharge", charge) /* Adding changes made to be sent back and read for the new entry in the RecyclerView. */
                setResult(RESULT_DELIVERY, intent) /* Setting the Result to pass the OK (-1) Result and including the intent with its data. */
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