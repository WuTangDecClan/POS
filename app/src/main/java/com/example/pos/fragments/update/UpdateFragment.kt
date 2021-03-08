package com.example.pos.fragments.update

import android.content.Intent
import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AlertDialogLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pos.*
import com.example.pos.model.Customer
import com.example.pos.viewmodel.POSViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var posViewModel: POSViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        posViewModel = ViewModelProvider(this).get(POSViewModel::class.java)

        view.updateTextName.setText(args.currentCustomer.name)
        view.updateTextNumber.setText(args.currentCustomer.mobile)
        view.updatePostalAddress.setText(args.currentCustomer.eircode)
        view.updateTextAddress.setText(args.currentCustomer.address)

        view.updateBtn.setOnClickListener {
            updateItem()
        }

        view.deleteBtn.setOnClickListener {
            deleteItem()
        }

        view.pickBtn.setOnClickListener {
            val name = updateTextName.text.toString()
            val number = updateTextNumber.text.toString()
            val postalCode = updatePostalAddress.text.toString()
            val address = updateTextAddress.text.toString()

            Toast.makeText(requireContext(), "Pick Button Clicked!", Toast.LENGTH_LONG).show()
           val intent = Intent()

            intent.putExtra("customerName", name)
            intent.putExtra("customerNumber", number)
            intent.putExtra("postalCode", postalCode)
            intent.putExtra("address", address)

           // setResult(RESULT_CODE_2, intent) /* Setting the Result to pass the OK (-1) Result and including the intent with its data. */
           // finish() /* Ending the  Activity. */
        }

        return view
    }

    private fun updateItem() {
        val name = updateTextName.text.toString()
        val number = updateTextNumber.text.toString()
        val postalCode = updatePostalAddress.text.toString()
        val address = updateTextAddress.text.toString()

        val customer = Customer(args.currentCustomer.id, name,address,postalCode,number)

        posViewModel.updateCustomer(customer)
        Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_LONG).show()
        // Returning to the List Fragment
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)

    }

    private fun deleteItem() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete Customer")
        builder.setMessage("\n\nAre you sure you want to delete ${args.currentCustomer.name}")
        builder.setPositiveButton("Yes") { _, _ ->
            posViewModel.deleteCustomer(args.currentCustomer)
            Toast.makeText(requireContext(), "Successfully deleted!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentCustomer.name}?")
        builder.create()
        builder.show()
        AlertDialogLayout.TEXT_ALIGNMENT_CENTER
    }

}

