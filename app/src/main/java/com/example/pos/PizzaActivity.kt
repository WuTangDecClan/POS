package com.example.pos

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.multiplerecyclerview.OrderAdapter
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.activity_pizza.*

const val SIZE_SMALL = 10
const val SIZE_LARGE = 12
const val ADD_TOP_SMALL = .90
const val ADD_TOP_LARGE = 1.10
const val ADD_PREM_SMALL = 1.3
const val ADD_PREM_LARGE = 1.5

class PizzaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza) /* Loading this layout if the activity is a pizza. */

        val pizzaName: TextView = findViewById(R.id.pizzaName)
        pizzaName.text = intent.getStringExtra("itemName").toString() /* Adding the name of the pizza to Screen. */
        val pizzaPrice: TextView = findViewById(R.id.pizzaPrice)
        pizzaPrice.text = intent.getStringExtra("itemPrice").toString() /* Adding the price of the pizza to Screen. */
        val pizzaSize: TextView = findViewById(R.id.pizzaSize)

        val topping1: EditText = findViewById(R.id.topping1Entry) /* Storing the View Topping 1 in the variable topping1. */
        val topping2: EditText = findViewById(R.id.topping2Entry)
        val topping3: EditText = findViewById(R.id.topping3Entry)
        val topping4: EditText = findViewById(R.id.topping4Entry)

        /* If the user clicks 10 inch then call the insertSize function and the setPrice function. */
        tenInchButton.setOnClickListener {
            tenInchButton.setTextColor(Color.rgb(136,27,50))
            twelveInchButton.setTextColor(Color.WHITE)
            Toast.makeText(this, "Ten inch selected", Toast.LENGTH_SHORT).show() /* Lets the user know. */
            insertSize(size = SIZE_SMALL.toString(), pizzaSize = pizzaSize) /* Calling to insert the Size, passing the arguments SIZE_SMALL & the View pizzaSlice. */
            setPrice(price = "9.90", pizzaPrice = pizzaPrice) /* Calling to set** the price of the item, passing the price and the View pizzaPrice. */
        }

        /* If the user clicks 12 inch then call the insertSize function and the setPrice function. */
        twelveInchButton.setOnClickListener {
            tenInchButton.setTextColor(Color.WHITE)
            twelveInchButton.setTextColor(Color.rgb(136,27,50))
            Toast.makeText(this, "Twelve inch selected", Toast.LENGTH_SHORT).show() /* Lets the user know. */
            insertSize(size = SIZE_LARGE.toString(), pizzaSize = pizzaSize) /* Calling to insert the Size, passing the arguments SIZE_LARGE & the View pizzaSlice. */
            setPrice(price = "11.8", pizzaPrice = pizzaPrice) /* Calling to set** the price of the item, passing the price and the View pizzaPrice. */
        }

        /* If the user clicks additional button to charge for addition: check size and call function to changePrice. */
        additionalButton.setOnClickListener {
            when { /* Using when to see what Size the item is, if its a certain size the price is adjusted to suit this. */
                pizzaSize.text == (SIZE_SMALL.toString()) -> {
                    changePrice(price = ADD_TOP_SMALL, textView = pizzaPrice)
                    Toast.makeText(this, "Charging for a Small Adding", Toast.LENGTH_SHORT).show() /* Lets the user whats being charged. */
                } pizzaSize.text == (SIZE_LARGE.toString()) -> {
                    changePrice(price = ADD_TOP_LARGE, textView = pizzaPrice)
                    Toast.makeText(this, "Large Sized Additional Adding", Toast.LENGTH_SHORT).show() /* Lets the user whats being charged. */
                } else ->  { /* If the size hasn't been picked then change the colors of the inches and warn the user. */
                    tenInchButton.setTextColor(Color.rgb(202,180,156))
                    twelveInchButton.setTextColor(Color.rgb(202,180,156))
                    Toast.makeText(this, "Missing Size Choice!", Toast.LENGTH_SHORT).show() /* Lets the user know they need to pick a size before charging for toppings. */
                }
            }
        }

        /* If the user clicks premium button to charge for addition: check size and call function to changePrice. */
        premiumButton.setOnClickListener {
            when { /* Using when to see what Size the item is, if its a certain size the price is adjusted to suit this. */
                pizzaSize.text == (SIZE_SMALL.toString()) -> {
                    changePrice(price = ADD_PREM_SMALL, textView = pizzaPrice)
                    Toast.makeText(this, "Small Sized Premium Topping", Toast.LENGTH_SHORT).show() /* Lets the user whats being charged. */
                } pizzaSize.text == (SIZE_LARGE.toString()) -> {
                changePrice(price = ADD_PREM_LARGE, textView = pizzaPrice)
                Toast.makeText(this, "Large Sized Premium Adding", Toast.LENGTH_SHORT).show() /* Lets the user whats being charged. */
            }
                else ->  { /* If the size hasn't been picked then change the colors of the inches and warn the user. */
                    tenInchButton.setTextColor(Color.rgb(202,180,156))
                    twelveInchButton.setTextColor(Color.rgb(202,180,156))
                    Toast.makeText(this, "Missing Size Choice!", Toast.LENGTH_SHORT).show() /* Lets the user know they need to pick a size before charging for toppings. */
                }
            }
        }

        /* completeBtn onClickListener, if pressed then load the entries and add them into an intent. Finish this Activity and return back to Order Activity. */
        completeBtn.setOnClickListener {

            val itemName: String = pizzaName.text.toString() /* Storing all the changes to the views that have been made. */
            val itemPrice: String = pizzaPrice.text.toString()
            val itemSize: String = pizzaSize.text.toString()

            if (itemSize == "10" || itemSize == "12") {
                val intent = Intent()  /* Creating an Intent. */
                intent.putExtra("itemName", itemName)
                intent.putExtra("itemPrice", itemPrice) /* Adding changes made to be sent back and read for the new entry in the RecyclerView. */
                intent.putExtra("itemSize", itemSize)

                if (!emptyAdditions(topping1) || !emptyAdditions(topping2) || !emptyAdditions(topping3) || !emptyAdditions(topping4)) { /* If the User added any information to toppings 1..4 put them extra too. */
                    intent.putExtra("topping1", "$topping1")
                    intent.putExtra("topping2", "$topping2")
                    intent.putExtra("topping3", "$topping3")
                    intent.putExtra("topping4", "$topping4")
                    setResult(Activity.RESULT_OK, intent) /* Setting the Result to pass the OK (-1) Result and including the intent with its data. */
                } else {
                    setResult(Activity.RESULT_OK, intent) /* Setting the Result to pass the OK (-1) Result and including the intent with its data. */
                    startActivity(intent) /* Starting Activity. */
                }
                finish() /* Ending the  Activity. */
            } else { /* If the size hasn't been picked then change the colors of the inches and warn the user. */
                tenInchButton.setTextColor(Color.rgb(202,180,156))
                twelveInchButton.setTextColor(Color.rgb(202,180,156))
                Toast.makeText(this, "Missing Size Choice!", Toast.LENGTH_SHORT).show() /* Lets the user know they need to pick a size before finishing. */
            }
        }
    }

    /* Function to check if the User has entered information into toppings or not. */
    private fun emptyAdditions(editText: EditText): Boolean {
        val msg: String = editText.text.toString()
        return msg.trim().isNotEmpty()

    }

    /* Changing the Size of the Item to the one selected. */
    private fun insertSize(size: String, pizzaSize: TextView) {
        pizzaSize.text = size
    }

    /* Setting the Price of the Item to the one selected. */
    private fun setPrice(price: String, pizzaPrice: TextView) {
        pizzaPrice.text = price
    }

    /* Changing the Price of the Item to the one selected. */
    private fun changePrice(price: Double, textView: TextView) {
        var cost = textView.text.toString()
        var total = (cost.toDouble() + price)
        textView.text = total.toString()
    }

}