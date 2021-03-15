package com.example.pos.MainBody.dashboard.ordersActivites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pos.R
import kotlinx.android.synthetic.main.activity_payment.*

const val PAYMENT = 22

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val total: String = intent.getStringExtra("total").toString()
        amountDueButton.text = "Amount Due: €$total "

        amountReturnButton.setOnClickListener {
            paymentType.text = amountReturnButton.tag.toString()
            var given: Double = moneyEntry.text.toString().toDouble()
            given -= (total.toDouble())
            given.toString()
            amountReturnButton.text = "Change Due: €$given"
        }

        val customerName: String = intent.getStringExtra("customerName").toString()
        val customerNumber: String = intent.getStringExtra("customerNumber").toString()
        val customerPostal: String = intent.getStringExtra("postalCode").toString()
        val customerAddress: String = intent.getStringExtra("address").toString()

        completePayment_btn.setOnClickListener {
            val intent = Intent()  /* Creating an Intent. */
            intent.putExtra("total", total) /* Adding the foodName to the intent. */
            intent.putExtra("customerName", customerName) /* Adding the foodName to the intent. */
            intent.putExtra("customerNumber", customerNumber) /* Adding the foodName to the intent. */
            intent.putExtra("customerPostal", customerPostal) /* Adding the foodName to the intent. */
            intent.putExtra("customerAddress", customerAddress) /* Adding the foodName to the intent. */
            intent.putExtra("paymentType", paymentType.text)
            intent.putExtra("total", total) /* Adding changes made to be sent back and read for the new entry in the RecyclerView. */
            setResult(PAYMENT, intent) /* Setting the Result to pass the OK (-1) Result and including the intent with its data. */
            finish() /* Ending the  Activity. */
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
    }
}