package com.example.pos.MainBody.dashboard.ordersActivites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pos.MainBody.dashboard.ordersActivites.recylerActiveOrders.AOrdersAdapter
import com.example.pos.R
import com.example.pos.model.ActiveOrderModel
import kotlinx.android.synthetic.main.fragment_list.*

class ActiveOrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_order)

        val aorderList = generateList(50)

        recyclerview.adapter = AOrdersAdapter(aorderList)
        recyclerview.layoutManager = LinearLayoutManager(this)

        //customer Information
        /*
        val customerName: String = intent.getStringExtra("customerName").toString()
        val customerNumber: String = intent.getStringExtra("customerNumber").toString()
        val customerPostal: String = intent.getStringExtra("postalCode").toString()
        val customerAddress: String = intent.getStringExtra("address").toString()

        // Payment Information
        val paymentType: String = intent.getStringExtra("paymentType").toString() /* storing the values from arguments returned. */
        val paymentTotal: String = intent.getStringExtra("total").toString()
        */
    }

    private fun generateList(size: Int): List<ActiveOrderModel> {

        val list = ArrayList<ActiveOrderModel>()


        for (i in 0 until size) {
            val item = ActiveOrderModel(
                "Declan Thorne",
                "0892142323",
                "A95 K4D3",
                "123 Fake Street, Glenageary",
                "22.00",
                "Gary's Gourmet Website"
            )
            list += item
        }
        return list
    }
}