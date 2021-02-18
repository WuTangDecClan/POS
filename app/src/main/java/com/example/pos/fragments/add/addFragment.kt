package com.example.pos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pos.model.Customer
import com.example.pos.viewmodel.POSViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class addFragment : Fragment() {

    private lateinit var _posViewModel: POSViewModel /* Initilising the View Model POSViewModel. */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        _posViewModel = ViewModelProvider(this).get(POSViewModel::class.java)

        view.add_btn.setOnClickListener {
            insertDataToDatabase()  /* Method to insert the data into the database */
        }

        return view /* Returning the view */
    }

    private fun insertDataToDatabase() { /* Getting the texts and adding them to the Customer Database */
        val name = editTextName.text.toString()
        val address = editTextTextAddress.text.toString()
        val eircode = editTextPostalAddress.text.toString()
        val mobile = editTextNumber.text.toString()

        /* Creating a Customer Object. */
        val customer = Customer(
            0,
            name,
            address,
            eircode,
            mobile
        )
        /* Adding Customer to the Room Database. */
        _posViewModel.addCustomer(customer)
        Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()

        // Returning to the List Fragment
        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }
}