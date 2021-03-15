package com.example.pos.MainBody.dashboard.ordersActivites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.pos.R
import kotlinx.android.synthetic.main.activity_side.*

const val SIDE_CODE_1 = 52
const val SIDE_CODE_2 = 53

class SideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_side)

        val sideName: TextView = findViewById(R.id.sideName)
        sideName.text = intent.getStringExtra("itemName").toString() /* Adding the name of the pizza to Screen. */
        val sidePrice: TextView = findViewById(R.id.sidePrice)
        sidePrice.text = intent.getStringExtra("itemPrice").toString() /* Adding the price of the pizza to Screen. */
        val price: String = sidePrice.text.toString().substring(startIndex = 0, endIndex = 3) //"10.8-12.8"

        val topping1: EditText = findViewById(R.id.topping1Entry) /* Storing the View Topping 1 in the variable topping1. */
        val topping2: EditText = findViewById(R.id.topping2Entry)

        setPrice(price = price, sidePrice = sidePrice)

        /* If the user clicks additional button to charge for addition: check size and call function to changePrice. */
        additionalSideButton.setOnClickListener {
            changePrice(price = ADD_TOP_SMALL, textView = sidePrice)
            Toast.makeText(this, "Additional Adding", Toast.LENGTH_SHORT).show() /* Lets the user whats being charged. */
        }

        /* completeBtn onClickListener, if pressed then load the entries and add them into an intent. Finish this Activity and return back to Order Activity. */
        completeSideBtn.setOnClickListener {

            val itemName: String = sideName.text.toString() /* Storing all the changes to the views that have been made. */
            val itemPrice: String = sidePrice.text.toString()
            val top1: String = topping1.text.toString()
            val top2: String = topping2.text.toString()

            val intent = Intent()  /* Creating an Intent. */
            intent.putExtra("itemName", itemName)
            intent.putExtra("itemPrice", itemPrice) /* Adding changes made to be sent back and read for the new entry in the RecyclerView. */

            if (emptyAdditions(top1) || emptyAdditions(top2)) {
                intent.putExtra("topping1", top1)
                intent.putExtra("topping2", top2)
                setResult(SIDE_CODE_2, intent) /* Setting the Result to pass the OK (-1) Result and including the intent with its data. */
                finish() /* Ending the  Activity. */
            } else {
                setResult(SIDE_CODE_1, intent) /* Setting the Result to pass the OK (-1) Result and including the intent with its data. */
                finish() /* Ending the  Activity. */
            }
        }
    }

    /* Function to check if the User has entered information into toppings or not. */
    private fun emptyAdditions(msg: String): Boolean {
        return msg.isNotEmpty()

    }

    /* Setting the Price of the Item to the one selected. */
    private fun setPrice(price: String, sidePrice: TextView) {
        sidePrice.text = price
    }

    /* Changing the Price of the Item to the one selected. */
    private fun changePrice(price: Double, textView: TextView) {
        var cost = textView.text.toString()
        var total = (cost.toDouble() + price)
        total = String.format("%.2f", total).toDouble()
        textView.text = total.toString()
    }

}

/*

        val sideName: TextView = findViewById(R.id.sideName)
        sideName.text = intent.getStringExtra("itemName").toString() /* Adding the name of the pizza to Screen. */
        val sidePrice: TextView = findViewById(R.id.sidePrice)
        sidePrice.text = intent.getStringExtra("itemPrice").toString() /* Adding the price of the pizza to Screen. */
        val price: String = sidePrice.text.toString().substring(startIndex = 0, endIndex = 3) //"10.8-12.8"

        val topping1: EditText = findViewById(R.id.topping1Entry) /* Storing the View Topping 1 in the variable topping1. */
        val topping2: EditText = findViewById(R.id.topping2Entry)

        //setPrice(price = price, sidePrice = sidePrice)

        /* If the user clicks additional button to charge for addition: check size and call function to changePrice. */
        additionalButton.setOnClickListener {
            changePrice(price = ADD_TOP_SMALL, textView = sidePrice)
            Toast.makeText(this, "Additional Adding", Toast.LENGTH_SHORT).show() /* Lets the user whats being charged. */
        }


        /* completeBtn onClickListener, if pressed then load the entries and add them into an intent. Finish this Activity and return back to Order Activity. */
        completeBtn.setOnClickListener {

            val itemName: String = sideName.text.toString() /* Storing all the changes to the views that have been made. */
            val itemPrice: String = sidePrice.text.toString()
            val top1: String = topping1.text.toString()
            val top2: String = topping2.text.toString()

            val intent = Intent()  /* Creating an Intent. */
            intent.putExtra("itemName", itemName)
            intent.putExtra("itemPrice", itemPrice) /* Adding changes made to be sent back and read for the new entry in the RecyclerView. */

            if (emptyAdditions(top1) || emptyAdditions(top2)) {
                intent.putExtra("topping1", top1)
                intent.putExtra("topping2", top2)
                setResult(RESULT_CODE_2, intent) /* Setting the Result to pass the OK (-1) Result and including the intent with its data. */
                finish() /* Ending the  Activity. */
            } else {
                setResult(RESULT_CODE_1, intent) /* Setting the Result to pass the OK (-1) Result and including the intent with its data. */
                finish() /* Ending the  Activity. */
            }
        }

 */