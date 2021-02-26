package com.example.pos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import com.example.pos.viewmodel.POSViewModel

class CustomerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"
    }
}