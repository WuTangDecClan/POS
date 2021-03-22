package com.example.pos.MainBody.dashboard.ordersActivites

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.pos.R
import com.example.pos.model.ActiveOrderModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mazenrashed.printooth.Printooth
import com.mazenrashed.printooth.data.printable.Printable
import com.mazenrashed.printooth.data.printable.RawPrintable
import com.mazenrashed.printooth.data.printable.TextPrintable
import com.mazenrashed.printooth.data.printer.DefaultPrinter
import com.mazenrashed.printooth.ui.ScanningActivity
import com.mazenrashed.printooth.utilities.Printing
import com.mazenrashed.printooth.utilities.PrintingCallback
import kotlinx.android.synthetic.main.activity_payment.*
import java.util.*

const val PAYMENT = 22

class PaymentActivity : AppCompatActivity(), PrintingCallback {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    /* Access a Cloud Firestore instance from the Activity. */
    val db = Firebase.firestore

    internal var printing: Printing?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val total: String = intent.getStringExtra("total").toString()
        amountDueButton.text = "Amount Due: €$total "

        database = FirebaseDatabase.getInstance("https://pos-system-317c9-default-rtdb.europe-west1.firebasedatabase.app/")
        reference = database.getReference("Users")

        amountReturnButton.setOnClickListener {
            paymentType.text = amountReturnButton.tag.toString()
            var given: Double = moneyEntry.text.toString().toDouble()
            given -= (total.toDouble())
            given.toString()
            amountReturnButton.text = "Change Due: €$given"
        }

        val customerName: String = intent.getStringExtra("customerName").toString()
        val customerNumber: String = intent.getStringExtra("customerNumber").toString()
        val customerPostal: String = intent.getStringExtra("customerPostal").toString()
        val customerAddress: String = intent.getStringExtra("customerAddress").toString()

        completePayment_btn.setOnClickListener {

            // Initialize a new instance of
            val builder = AlertDialog.Builder(this)
            // Set the alert dialog title
            builder.setTitle("Complete Order")
            // Display a message on alert dialog
            builder.setMessage("Are you sure you want to complete this order? ")
            // Set a positive button and its click listener on alert dialog
            builder.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                val c = Calendar.getInstance()

                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)+1
                val day = c.get(Calendar.DAY_OF_MONTH)

                val hour = c.get(Calendar.HOUR_OF_DAY)
                val minute = c.get(Calendar.MINUTE)
                val time = "$hour.$minute"
                val type: String = paymentType.text.toString()
                val totalpaid: Double = total.toDouble()

                /* Creating a new Order.  */
                val order = hashMapOf("customer Name" to customerName, "customer Number" to customerNumber, "eircode" to customerPostal, "address" to customerAddress, "payment Total" to total, "payment Amount" to type, "Date of Order" to time)

                val custNam = customerName.replace(" ","")
                val custNum = customerNumber.replace(" ","")
                db.collection("Orders - $day.$month.$year").document("$custNam$custNum").set(order)

                val docRef =  db.collection("Total Sales").document("$day.$month.$year")
                docRef.get()
                    .addOnSuccessListener { document ->
                        if(document.exists()) {
                            if (type == "Cash")
                                docRef.update("Sales Cash", FieldValue.increment(totalpaid))
                            else if (type == "Just Eat")
                                docRef.update("Sales Just Eat", FieldValue.increment(totalpaid))
                            else if (type == "Deliveroo")
                                docRef.update("Sales Deliveroo", FieldValue.increment(totalpaid))
                            else if (type == "Gary's Gourmet Website")
                                docRef.update("Sales Gary's Gourmet Website", FieldValue.increment(totalpaid))
                            else
                                docRef.update("Sales Visa", FieldValue.increment(totalpaid))
                        } else {
                            /* Creating a new Order.  */
                            val sales = hashMapOf("Sales Cash" to 0.0, "Sales Just Eat" to 0.0, "Sales Deliveroo" to 0.0, "Sales Gary's Gourmet Website" to 0.0, "Sales Visa" to 0.0)
                            db.collection("Total Sales").document("$day.$month.$year").set(sales)
                            if (type == "Cash")
                                docRef.update("Sales Cash", FieldValue.increment(totalpaid))
                            else if (type == "Just Eat")
                                docRef.update("Sales Just Eat", FieldValue.increment(totalpaid))
                            else if (type == "Deliveroo")
                                docRef.update("Sales Deliveroo", FieldValue.increment(totalpaid))
                            else if (type == "Gary's Gourmet Website")
                                docRef.update("Sales Gary's Gourmet Website", FieldValue.increment(totalpaid))
                            else
                                docRef.update("Sales Visa", FieldValue.increment(totalpaid))
                        }
                    }

                //initView()
                val intent = Intent()  /* Creating an Intent. */
                setResult(PAYMENT, intent) /* Setting the Result to pass the OK (-1) Result and including the intent with its data. */
                finish() /* Ending the  Activity. */
            }
            // Display a negative button on alert dialog
            builder.setNegativeButton("No") { _: DialogInterface, _: Int ->

            }
            builder.show()

        }

        amountDueButton.setOnClickListener {
            paymentType.text = amountDueButton.tag.toString()
        }

        paymentMoneySymbol.setOnClickListener {
            paymentType.text = paymentMoneySymbol.tag.toString()
        }

        justEatView.setOnClickListener {
            paymentType.text = justEatView.tag.toString()
        }

        deliverooView.setOnClickListener {
            paymentType.text = deliverooView.tag.toString()
        }

        garysView.setOnClickListener {
            paymentType.text = garysView.tag.toString()
        }

        visaView.setOnClickListener {
            paymentType.text = visaView.tag.toString()
        }

        delivery_btn.setOnClickListener {
            orderMethodType.text = delivery_btn.tag.toString()
        }

        collection_btn.setOnClickListener {
            orderMethodType.text = collection_btn.tag.toString()
        }
    }

    private fun initView() {
        if(printing != null)
            printing!!.printingCallback = this

        printOrder()

    }

    private fun printOrder() {
        val print = ArrayList<Printable>()
        print.add(RawPrintable.Builder(byteArrayOf(27,100,4)).build())

        print.add(TextPrintable.Builder()
            .setText("Order 001")
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setNewLinesAfter(1)
            .build())

        print.add(TextPrintable.Builder()
            .setText("$paymentType.text.toString()")
            .setLineSpacing(DefaultPrinter.LINE_SPACING_60)
            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
            .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
            .setUnderlined(DefaultPrinter.UNDERLINED_MODE_ON)
            .setNewLinesAfter(1)
            .build())

        printing!!.print(print)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ScanningActivity.SCANNING_FOR_PRINTER && resultCode == Activity.RESULT_OK)
            initPriniting();
    }

    private fun initPriniting() {
        printing = Printooth.printer()

        if (printing != null)
            printing!!.printingCallback = this
    }

    override fun connectingWithPrinter() {
        Toast.makeText(this, "Connecting to the printer...", Toast.LENGTH_SHORT).show()
    }

    override fun connectionFailed(error: String) {
        Toast.makeText(this, "Failed to connect...", Toast.LENGTH_SHORT).show()
    }

    override fun onError(error: String) {
        Toast.makeText(this, "Error Printing...", Toast.LENGTH_SHORT).show()
    }

    override fun onMessage(message: String) {
        Toast.makeText(this, "Message $message...", Toast.LENGTH_SHORT).show()
    }

    override fun printingOrderSentSuccessfully() {
        Toast.makeText(this, "Order successfully Printed...", Toast.LENGTH_SHORT).show()
    }
}