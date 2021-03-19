package com.example.pos.MainBody.dashboard.ordersActivites

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.pos.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.activity_pizza.*
import java.util.*

const val SIZE_SMALL = 10
const val SIZE_LARGE = 12
const val ADD_TOP_SMALL = .90
const val ADD_TOP_LARGE = 1.10
const val ADD_PREM_SMALL = 1.3
const val ADD_PREM_LARGE = 1.5

const val RESULT_CODE_1 = 1
const val RESULT_CODE_2 = 2


class PizzaActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    /* Access a Cloud Firestore instance from the Activity. */
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza) /* Loading this layout if the activity is a pizza. */

        database = FirebaseDatabase.getInstance("https://pos-system-317c9-default-rtdb.europe-west1.firebasedatabase.app/")
        reference = database.getReference("Food")


        val pizzaName: TextView = findViewById(R.id.pizzaName)
        pizzaName.text = intent.getStringExtra("itemName").toString() /* Adding the name of the pizza to Screen. */
        val pizzaPrice: TextView = findViewById(R.id.pizzaPrice)
        pizzaPrice.text = intent.getStringExtra("itemPrice").toString() /* Adding the price of the pizza to Screen. */
        val smallPrice: String = pizzaPrice.text.toString().substring(startIndex = 0, endIndex = 4) //"10.8-12.8"
        val largePrice: String = pizzaPrice.text.toString().substring(startIndex = 5, endIndex = 9)
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
            setPrice(price = smallPrice, pizzaPrice = pizzaPrice) /* Calling to set** the price of the item, passing the price and the View pizzaPrice. */
        }

        /* If the user clicks 12 inch then call the insertSize function and the setPrice function. */
        twelveInchButton.setOnClickListener {
            tenInchButton.setTextColor(Color.WHITE)
            twelveInchButton.setTextColor(Color.rgb(136,27,50))
            Toast.makeText(this, "Twelve inch selected", Toast.LENGTH_SHORT).show() /* Lets the user know. */
            insertSize(size = SIZE_LARGE.toString(), pizzaSize = pizzaSize) /* Calling to insert the Size, passing the arguments SIZE_LARGE & the View pizzaSlice. */
            setPrice(price = largePrice, pizzaPrice = pizzaPrice) /* Calling to set** the price of the item, passing the price and the View pizzaPrice. */
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
            val top1: String = topping1.text.toString()
            val top2: String = topping2.text.toString()
            val top3: String = topping3.text.toString()
            val top4: String = topping4.text.toString()

            if (itemSize == "10" || itemSize == "12") {
                val intent = Intent()  /* Creating an Intent. */
                intent.putExtra("itemName", itemName)
                intent.putExtra("itemPrice", itemPrice) /* Adding changes made to be sent back and read for the new entry in the RecyclerView. */
                intent.putExtra("itemSize", itemSize)

                if (emptyAdditions(top1) || emptyAdditions(top2) || emptyAdditions(top3) || emptyAdditions(top4) ) {
                    intent.putExtra("topping1", top1)
                    intent.putExtra("topping2", top2)
                    intent.putExtra("topping3", top3)
                    intent.putExtra("topping4", top4)
                    setResult(RESULT_CODE_2, intent) /* Setting the Result to pass the OK (-1) Result and including the intent with its data. */
                } else {
                    setResult(RESULT_CODE_1, intent) /* Setting the Result to pass the OK (-1) Result and including the intent with its data. */
                }
                val c = Calendar.getInstance()

                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)+1
                val day = c.get(Calendar.DAY_OF_MONTH)

                val foodNam = itemName.replace(" ","")
                val foodSiz = itemSize.replace(" ","")

                val docRef =  db.collection("Food Sales - $day.$month.$year").document("$foodNam$foodSiz")
                docRef.get()
                    .addOnSuccessListener { document ->
                        if(document.exists()) {
                            docRef.update("Sales Number", FieldValue.increment(1))
                        } else {
                            /* Creating a new Order.  */
                            val food = hashMapOf(
                                "Item Name" to itemName,
                                "Item Size" to itemSize,
                                "Sales Number" to 1)
                            db.collection("Food Sales - $day.$month.$year").document("$foodNam$foodSiz").set(food)
                        }
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
    private fun emptyAdditions(msg: String): Boolean {
        return msg.isNotEmpty()

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
        total = String.format("%.2f", total).toDouble()
        textView.text = total.toString()
    }

}