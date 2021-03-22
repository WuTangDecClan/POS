package com.example.pos.MainBody.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.multiplerecyclerview.OrderAdapter
import com.example.pos.*
import com.example.pos.MainBody.dashboard.ordersActivites.*
import com.example.pos.model.DataModel
import kotlinx.android.synthetic.main.activity_order.*

const val INDEX = 0

/* Order Activity is an activity that shows the screen for creating an order. */
class OrderActivity : AppCompatActivity() {
/* Data Model:
itemName, itemQuantity, itemPrice, topping1, topping2, topping3, topping4, delivery, sideName, sidePrice, openFoodDetails, openFoodCharge, customerName, customerNumber, customerPostal, customerAddress paymentAmount, paymentType, viewType */

    val list = ArrayList<DataModel>() /*ArrayList that is type Data Model. */
    val orderAdapter = OrderAdapter(this, getItemsList()) /* Adapter class is initialized and list is passed in the param. */

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("", "OnCreate: Order Activity.\n")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order) /* Choosing the activity_order.xml. */

        orderRecyclerView.layoutManager = LinearLayoutManager(this) /* Set the LayoutManager that this RecyclerView will use. */
        orderRecyclerView.adapter = orderAdapter  /* Adapter instance is set to the recyclerview to inflate the items. */

        /* The Activity before Order Activity may pass some extra variables. So in that case they are passed. */
        val customerName: String = intent.getStringExtra("customerName").toString()
        val customerNumber: String = intent.getStringExtra("customerNumber").toString()
        val customerPostal: String = intent.getStringExtra("postalCode").toString()
        val customerAddress: String = intent.getStringExtra("address").toString()
        /* If the Activity before Order Activity hasn't passed any information then do nothing. */
        if (customerName == "null")
        /* If the Activity before Order Activity hasn passed any information then add it to the Recycler View with Customer Type. */
        else
            insertCustomer(customerName, customerNumber, customerPostal, customerAddress)

        customerButton.setOnClickListener { /* Customer onclick button: Gets taken to the customer screen to add a customer. */
            val intent = Intent(this@OrderActivity, CustomerQuickActivity::class.java)  /* Creating an Intent to go to Customer Quick Activity. */
            startActivityForResult(intent,1) /* Starting Activity for result. */
        }

        deliveryChargeButton.setOnClickListener { /* Delivery onclick button: Gets taken to the delivery screen to choose a delivery charge. */
            val intent = Intent(this@OrderActivity, DeliveryActivity::class.java)  /* Creating an Intent to go to Delivery Activity. */
            startActivityForResult(intent,1) /* Starting Activity for result. */
        }

        openFoodButton.setOnClickListener {  /* OpenFood onclick button: Gets taken to the Open Food screen to add any open food charge necessary. */
            val intent = Intent(this@OrderActivity, OpenFoodActivity::class.java)  /* Creating an Intent to go to Open Food Activity. */
            startActivityForResult(intent,1) /* Starting Activity for result. */
        }

        cancelOrderButton.setOnClickListener {/* cancelOrder onclick button: Return to the Dashboard, finish the activity, cancel the order.. */
            val intent = Intent(this@OrderActivity, Dashboard::class.java)  /* Creating an Intent to go to Dashboard Activity. */
            startActivity(intent) /* Starting Activity. */
            finish() /* Finishing the Order Activity. */
        }

        /* If the User clicks complete order. */
        complete_btn.setOnClickListener {

            val total = textViewPrice.text.toString() /* Gathering the total from the order made. */
            val intent = Intent(this@OrderActivity, PaymentActivity::class.java)  /* Creating an Intent to go to Payment Activity. */
            intent.putExtra("total", total) /* Adding the foodName to the intent. */
            intent.putExtra("customerName", customerName) /* Adding the foodName to the intent. */
            intent.putExtra("customerNumber", customerNumber) /* Adding the foodName to the intent. */
            intent.putExtra("customerPostal", customerPostal) /* Adding the foodName to the intent. */
            intent.putExtra("customerAddress", customerAddress) /* Adding the foodName to the intent. */
            startActivityForResult(intent,1) /* Starting Activity for result. */
        }
    }

    /* This function handles when Activities return to Order Activity and carry extra information. */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data) /* The parameters that get returned too. */
        Log.i("Order", "OnActivityResult.\n") /* Adding Logging information. */

        var total = textViewPrice.text.toString().toDouble() /* Getting the price and holding it as a double. */

        if (resultCode == PAYMENT) { /* If the result code returned is == Payment it means we are returning from Payment Activity. */
            /* Customer Information passed back from Payment Activity. */
            val customerName: String = intent.getStringExtra("customerName").toString() /* storing the values from arguments returned. */
            val customerNumber: String = intent.getStringExtra("customerNumber").toString()
            val customerPostal: String = intent.getStringExtra("postalCode").toString()
            val customerAddress: String = intent.getStringExtra("address").toString()
            /* Payment Information passed back from Payment Activity. */
            val paymentType: String = data?.getStringExtra("paymentType").toString() /* storing the values from arguments returned. */
            val paymentTotal: String = data?.getStringExtra("total").toString()

            val intent = Intent(this@OrderActivity, ActiveOrderActivity::class.java)  /* Creating an Intent to go to Active Order Activity. */
            intent.putExtra("paymentType", paymentType).toString()  /* Adding all the information needed to be passed to next Activity. */
            intent.putExtra("paymentTotal", paymentTotal).toString()
            intent.putExtra("customerName", customerName)
            intent.putExtra("customerNumber", customerNumber)
            intent.putExtra("customerPostal", customerPostal)
            intent.putExtra("customerAddress", customerAddress)
            startActivityForResult(intent,1) /* Starting Activity for result. */
        }

        if (resultCode == RESULT_DELIVERY) { /* If the result code returned is == Payment it means we are returning from Delivery Charge Activity. */
            val charge: String = data?.getStringExtra("deliveryCharge").toString() /* storing the values from arguments returned. */

            total += charge.toDouble() /* Adding the Delivery Charge chosen to the total cost. */
            total = String.format("%.2f", total).toDouble() /* Changing the format it rounds to .2 decimals. */
            textViewPrice.text = total.toString() /* Changing the total Price view to the new total price. */

            insertCharge(charge = charge) /* Calling function to insert charge. */
        }

        if (resultCode == RESULT_CODE_1 || resultCode == RESULT_CODE_2) { /* If the result is RESULT_CODE_1 OR RESULT_CODE_2. */
            /* Calling the insertItem function to add a View to the RecyclerView. */
            val itemName: String = data?.getStringExtra("itemName").toString() /* storing the values from arguments returned. */
            val itemPrice: String = data?.getStringExtra("itemPrice").toString() /* storing the values from arguments returned. */
            val itemSize: String = data?.getStringExtra("itemSize").toString() /* storing the values from arguments returned. */
            val top1: String = data?.getStringExtra("topping1").toString() /* storing the values from arguments returned. */
            val top2: String = data?.getStringExtra("topping2").toString() /* storing the values from arguments returned. */
            val top3: String = data?.getStringExtra("topping3").toString() /* storing the values from arguments returned. */
            val top4: String = data?.getStringExtra("topping4").toString() /* storing the values from arguments returned. */

            total += itemPrice.toDouble() /* Adding the Delivery Charge chosen to the total cost. */
            total = String.format("%.2f", total).toDouble() /* Changing the format it rounds to .2 decimals. */
            textViewPrice.text = total.toString() /* Changing the total Price view to the new total price. */

            if (resultCode == RESULT_CODE_1) /* Then it is a Pizza Item with no extra information or toppings. */
                insertItem(name = itemName, price = itemPrice, size = itemSize)
            else if (resultCode == RESULT_CODE_2) /* Then it is a Pizza Item with extra information or toppings or both. */
                insertItem4(name = itemName, price = itemPrice, size = itemSize, top1 = top1, top2 = top2, top3 = top3, top4 = top4)
        }

        if (resultCode == CUSTOMER_CHOICE) { /* If the result code returned is == CUSTOMER_CHOICE it means we are returning from Quick Customer Activity. */
            /* Adding the Customer to the Order/RecyclerView. */
            val customerName: String = data?.getStringExtra("customerName").toString()
            val customerNumber: String = data?.getStringExtra("customerNumber").toString()
            val customerPostal: String = data?.getStringExtra("postalCode").toString()
            val customerAddress: String = data?.getStringExtra("address").toString()
            /* Calling the function insertCustomer to insert the customer into the Order/RecyclerView. */
            insertCustomer(customerName, customerNumber, customerPostal, customerAddress)
        }

        if (resultCode == SIDE_CODE_1 || resultCode == SIDE_CODE_2) { /* If the result is SIDE_CODE_1 OR SIDE_CODE_2. */
            val itemName: String = data?.getStringExtra("itemName").toString() /* storing the values from arguments returned. */
            val itemPrice: String = data?.getStringExtra("itemPrice").toString()
            val top1: String = data?.getStringExtra("topping1").toString() /* storing the values from arguments returned. */
            val top2: String = data?.getStringExtra("topping2").toString()

            total += itemPrice.toDouble() /* Adding the Delivery Charge chosen to the total cost. */
            total = String.format("%.2f", total).toDouble() /* Changing the format it rounds to .2 decimals. */
            textViewPrice.text = total.toString() /* Changing the total Price view to the new total price. */

            if (resultCode == SIDE_CODE_1) /* Then it is a Side Item with no extra information or toppings. */
                insertSide(name = itemName, price = itemPrice)
            else if (resultCode == SIDE_CODE_2)  /* Then it is a Side Item with extra information or toppings or both. */
                insertSide2(name = itemName, price = itemPrice, top1 = top1, top2 = top2)
        }

        if (resultCode == RESULT_OPEN_FOOD) { /* If the result code returned is == Open Food it means we are returning from Open Food Activity. */

            val openFoodDetails: String = data?.getStringExtra("openFoodDetails").toString() /* storing the values from arguments returned. */
            val openFoodPrice: String = data?.getStringExtra("openFoodPrice").toString() /* storing the values from arguments returned. */

            total += openFoodPrice.toDouble()  /* Adding the Delivery Charge chosen to the total cost. */
            total = String.format("%.2f", total).toDouble() /* Changing the format it rounds to .2 decimals. */
            textViewPrice.text = total.toString()  /* Changing the total Price view to the new total price. */

            /* Calling the function insertOpenFood to insert the open food into the Order/RecyclerView. */
            insertOpenFood(openFoodDetails = openFoodDetails, openFoodPrice = openFoodPrice)
        }
    }

    /* Function to insert a customer into the Order/RecyclerView. */
    private fun insertCustomer(customerName: String, customerNumber: String, customerPostal: String, customerAddress: String) {
        val newItem = DataModel(customerName = customerName, customerNumber = customerNumber, customerPostal = customerPostal, customerAddress = customerAddress, viewType = OrderAdapter.CUSTOMER) /* Adding the item with correct arguments */
        list.add(INDEX, newItem) /* Adding Item at Position Index. */
        orderAdapter.notifyItemInserted(INDEX) /* Notifying the Adapter of the addition. */

    }

    /* Function to insert a side into the Order/RecyclerView. */
    private fun insertSide(name: String, price: String) {
        val newItem = DataModel(sidePrice = price, sideName = name, viewType = OrderAdapter.SIDE_ORDER_TYPE_1) /* Adding the item with correct arguments */
        list.add(INDEX, newItem) /* Adding Item at Position Index. */
        orderAdapter.notifyItemInserted(INDEX) /* Notifying the Adapter of the addition. */
    }

    /* Function to insert a side with extras into the Order/RecyclerView. */
    private fun insertSide2(name: String, price: String, top1: String, top2: String) {
        val newItem = DataModel(sidePrice = price, sideName = name, topping1 = top1, topping2 = top2, viewType = OrderAdapter.SIDE_ORDER_TYPE_2) /* Adding the item with correct arguments */
        list.add(INDEX, newItem) /* Adding Item at Position Index. */
        orderAdapter.notifyItemInserted(INDEX) /* Notifying the Adapter of the addition. */
    }

    /* Function to insert the delivery charge into the Order/RecyclerView. */
    private fun insertCharge(charge: String) {
        val newItem = DataModel(delivery = charge, viewType = OrderAdapter.DELIVERY_CHARGE) /* Adding the item with correct arguments */
        list.add(INDEX, newItem) /* Adding Item at Position Index. */
        orderAdapter.notifyItemInserted(INDEX) /* Notifying the Adapter of the addition. */
    }

    /* This function is invoked to insert Items into the RecyclerView. */
    fun insertItem(name: String, price: String, size: String) {
        val newItem = DataModel(itemName = name, itemQuantity = size, itemPrice = price, viewType = OrderAdapter.NO_TOPPING) /* Adding the item with correct arguments */
        list.add(INDEX, newItem) /* Adding Item at Position Index. */
        orderAdapter.notifyItemInserted(INDEX) /* Notifying the Adapter of the addition. */
    }

    /* This function is invoked to insert Items with Extras into the RecyclerView. */
    fun insertItem4(name: String, price: String, size: String, top1: String, top2: String, top3: String, top4: String) {
        val newItem = DataModel(name, size, price, top1, top2, top3, top4, viewType = OrderAdapter.TOPPINGS_4) /* Adding the item with correct arguments */
        list.add(INDEX, newItem) /* Adding Item at Position Index. */
        orderAdapter.notifyItemInserted(INDEX) /* Notifying the Adapter of the addition. */
    }

    /* This function is invoked to insert an Open Food into the RecyclerView. */
    private fun insertOpenFood(openFoodDetails: String, openFoodPrice: String) {
        val newItem = DataModel(openFoodDetails = openFoodDetails, openFoodCharge = "$openFoodPrice", viewType = OrderAdapter.OPEN_FOOD_CHARGE) /* Adding the item with correct arguments */
        list.add(INDEX, newItem) /* Adding Item at Position Index. */
        orderAdapter.notifyItemInserted(INDEX) /* Notifying the Adapter of the addition. */
    }

    /* Returns the number of items in the List Array. */
    private fun getItemsList(): ArrayList<DataModel> {
        return list
    }

    /* Pizza Button is whenever a pizza button is clicked this function gets invoked. It takes the name and price and creates an Intent to Pizza Activity. */
    fun pizzaButton(view: View) {
        val buttonView : Button = view as Button
        val texty = buttonView.text.toString()
        val price = buttonView.tag.toString()
        var intent = Intent(this@OrderActivity, PizzaActivity::class.java)  /* Creating an Intent to go to Pizza Activity. */
        intent.putExtra("itemName", texty) /* Adding the foodName to the intent. */
        intent.putExtra("itemPrice", price) /* Adding the foodPrice to the intent. */
        startActivityForResult(intent,1) /* Starting Activity for result. */
    }

    /* Side Button is whenever a side item button is clicked this function gets invoked. It takes the name and price and creates an Intent to Side Activity. */
    fun sideButton(view: View) {
        val buttonView : Button = view as Button
        val texty = buttonView.text.toString()
        val price = buttonView.tag.toString()
        var intent = Intent(this@OrderActivity, SideActivity::class.java)  /* Creating an Intent to go to Pizza Activity. */
        intent.putExtra("itemName", texty) /* Adding the foodName to the intent. */
        intent.putExtra("itemPrice", price) /* Adding the foodPrice to the intent. */
        startActivityForResult(intent,1) /* Starting Activity for result. */
    }

    /* Function to remove items from the RecyclerView. */
    fun removeItem() {
        list.removeAt(INDEX)
        orderAdapter.notifyItemRemoved(INDEX) /* Notifying the Adapter of the deletion. */

    }
    /* Printing onStart() to Logcat. */
    override fun onStart() {
        super.onStart()
        Log.i("", "OnStart: Order Activity.\n")
    }

    /* Printing onRestoreInstanceState() to Logcat. */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("", "OnRestoreInstanceState: Order Activity.\n")
    }

    /* Printing onResume() to Logcat. */
    override fun onResume() {
        super.onResume()
        Log.i("", "OnResume: Order Activity.\n")
    }

    /* Printing onPause() to Logcat. */
    override fun onPause() {
        super.onPause()
        Log.i("", "OnPause: Order Activity.\n")
    }

    /* Printing onStop() to Logcat. */
    override fun onStop() {
        super.onStop()
        Log.i("", "OnStop: Order Activity.\n")
    }

    /* Printing onDestroy() to Logcat. */
    override fun onDestroy() {
        super.onDestroy()
        Log.i("", "OnDestroy: Order Activity.\n")
    }

    /* Printing onRestart() to Logcat. */
    override fun onRestart() {
        super.onRestart()
        Log.i("", "OnRestart: Order Activity.\n")
    }
} /* Ending class. */

