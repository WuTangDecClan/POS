package com.example.pos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

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