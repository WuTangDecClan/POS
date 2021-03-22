package com.example.pos.MainBody.dashboard

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.pos.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_view_sales.*
import java.util.*
import kotlin.math.roundToLong

/* View Sales Activity is an activity that shows the User the Sales for the day. */
class ViewSalesActivity : AppCompatActivity() {

    /* Access a Cloud Firestore instance from the Activity. */
    val db = Firebase.firestore

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("", "OnCreate: View Sales Activity.\n")
        setContentView(R.layout.activity_view_sales)
        numbers()

        /* If the print button is clicked then show a builder. */
        printButton.setOnClickListener {
            val builder = AlertDialog.Builder(this) /* Initialize a new instance of Builder. */
            builder.setTitle("Complete Order")  /* Set the alert dialog title. */
            builder.setMessage("Are you sure you want to print Todays Sales?") /* Display a message on alert dialog. */
            builder.setPositiveButton("Yes") { _: DialogInterface, _: Int -> /* Set a positive button and its click listener on alert dialog. */
                Toast.makeText(this, "Printing Today's Sales...", Toast.LENGTH_LONG).show()
            }
            builder.setNegativeButton("No") { _: DialogInterface, _: Int -> /* Display a negative button on alert dialog. */
                Toast.makeText(this, "Printing Cancelled...", Toast.LENGTH_LONG).show()
            }
            builder.show() /* Showing the builder when clicked. */
        }

        /* If the Items Button is clicked then go to View Individual Sales Activity. */
        itemsButton.setOnClickListener {
            val intent = Intent(this@ViewSalesActivity, ViewIndividualSalesActivity::class.java)  /* Creating an Intent to go to Dashboard. */
            startActivity(intent) /* Starting Activity. */
        }

        /* Percentage Button works out the percentages of total sales. */
        percentagesButton.setOnClickListener {
            var day = totalAmountDayView.text.toString().toDouble()
            var month = totalAmountMonthView.text.toString().toDouble()
            var month1  = totalAmountMonthView.text.toString().toDouble() / 100

            var total = day - month
            total = (total * 1000.0).roundToLong() / 1000.0
            total = Math.round(total * 100.0) / 100.0

            var percent = total / month1
            percent = (percent * 1000.0).roundToLong() / 1000.0
            percent = (percent * 100.0).roundToLong() / 100.0

            if (total > 0) {
                totalAmountDayMoneyView.text = "+ $total €"
                totalAmountDayPercentView.text = "+ $percent %"
                totalAmountDayMoneyView.setTextColor(Color.rgb(21,120,17))
                totalAmountDayPercentView.setTextColor(Color.rgb(21,120,17))

            } else {
                totalAmountDayMoneyView.text = "€ $total"
                totalAmountDayPercentView.text = "%  $percent"
                totalAmountDayMoneyView.setTextColor(Color.rgb(204,6,5))
                totalAmountDayPercentView.setTextColor(Color.rgb(204,6,5))
            }
            day = totalAmountDayView.text.toString().toDouble()
            month = totalAmountYearView.text.toString().toDouble()
            month1  = totalAmountYearView.text.toString().toDouble() / 100
            total = day - month
            total = Math.round(total * 1000.0) / 1000.0
            total = Math.round(total * 100.0) / 100.0
            percent = total / month1
            percent = Math.round(percent * 1000.0) / 1000.0
            percent = Math.round(percent * 100.0) / 100.0
            if (total > 0) {
                totalAmountYearMoneyView.text = "+ $total €"
                totalAmountYearPercentView.text = "+ $percent %"
                totalAmountYearMoneyView.setTextColor(Color.rgb(21,120,17))
                totalAmountYearPercentView.setTextColor(Color.rgb(21,120,17))
            } else {
                totalAmountYearMoneyView.text = "€ $total"
                totalAmountYearPercentView.text = "%  $percent"
                totalAmountYearMoneyView.setTextColor(Color.rgb(204,6,5))
                totalAmountYearPercentView.setTextColor(Color.rgb(204,6,5))
            }
        }
    }

    private fun numbers() {

        val c = Calendar.getInstance()
        val yearDay = c.get(Calendar.YEAR)
        val monthDay = c.get(Calendar.MONTH) + 1
        val dayDay = c.get(Calendar.DAY_OF_MONTH)
        val monthMonth = c.get(Calendar.MONTH)
        val yearYear = c.get(Calendar.YEAR) - 1

        /* Changing the Cash Sales View to displays Sales in Cash for today.*/
        val docRefDay = db.collection("Total Sales").document("$dayDay.$monthDay.$yearDay")
        val docRefMonth = db.collection("Total Sales").document("$dayDay.$monthMonth.$yearDay")
        val docRefYear = db.collection("Total Sales").document("$dayDay.$monthDay.$yearYear")

        salesTodayDate.text = "$dayDay/$monthDay/$yearDay"
        salesMonthDate.text = "$dayDay/$monthMonth/$yearDay"
        salesYearDate.text = "$dayDay/$monthDay/$yearYear"
        docRefDay.get().addOnSuccessListener { document ->
            if (document.exists()) {
                cashAmountTodayView.text = document.getDouble("Sales Cash").toString()
                garyAmountTodayView.text = document.getDouble("Sales Deliveroo").toString()
                justEatAmountTodayView.text = document.getDouble("Sales Gary's Gourmet Website").toString()
                deliverooAmountTodayView.text = document.getDouble("Sales Just Eat").toString()
                visaAmountDayView.text = document.getDouble("Sales Visa").toString()
                totalAmountDayView.text = cashAmountTodayView.text.toString()

            }
        }

        docRefMonth.get().addOnSuccessListener { document ->
            if (document.exists()) {
                cashAmountMonthView.text = document.getDouble("Sales Cash").toString()
                garyAmountMonthView.text = document.getDouble("Sales Deliveroo").toString()
                justEatAmountMonthView.text = document.getDouble("Sales Gary's Gourmet Website").toString()
                deliverooAmountMonthView.text = document.getDouble("Sales Just Eat").toString()
                visaAmountMonthView.text = document.getDouble("Sales Visa").toString()
                totalAmountMonthView.text = document.getDouble("Sales Total").toString()

            }
        }

        docRefYear.get().addOnSuccessListener { document ->
            if (document.exists()) {
                cashAmountYearView.text = document.getDouble("Sales Cash").toString()
                garyAmountYearView.text = document.getDouble("Sales Deliveroo").toString()
                justEatAmountYearView.text = document.getDouble("Sales Gary's Gourmet Website").toString()
                deliverooAmountYearView.text = document.getDouble("Sales Just Eat").toString()
                visaAmountYearView.text = document.getDouble("Sales Visa").toString()
                totalAmountYearView.text = document.getDouble("Sales Total").toString()
            }
        }
    }
}