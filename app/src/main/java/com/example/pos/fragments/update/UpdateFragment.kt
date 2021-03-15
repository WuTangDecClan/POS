package com.example.pos.fragments.update

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.util.Log
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
import com.example.pos.MainBody.dashboard.OrderActivity
import com.example.pos.model.Customer
import com.example.pos.viewmodel.POSViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>() /* Returns a Lazy delegate to access the Fragment's arguments as an Args instance. */
    private lateinit var posViewModel: POSViewModel /* Storing the View Model in variable posViewModel. */

    /* Access a Cloud Firestore instance from the Activity. */
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /* Inflate the layout for this fragment. */
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        /* Creates ViewModelProvider. This will create ViewModels and retain them in a store of the given ViewModelStoreOwner. */
        posViewModel = ViewModelProvider(this).get(POSViewModel::class.java)

        view.updateTextName.setText(args.currentCustomer.name)  /* Getting the Customer's Name and setting it as the view Name. */
        view.updateTextNumber.setText(args.currentCustomer.mobile) /* Getting the Customer's Mobile and setting it as the view Number. */
        view.updatePostalAddress.setText(args.currentCustomer.eircode) /* Getting the Customer's Eircode and setting it as the view Postal Address. */
        view.updateTextAddress.setText(args.currentCustomer.address) /* Getting the Customer's Address and setting it as the view Address. */

        db.collection("customers").get().addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }.addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        /* If the user clicks the update Button then updateItem function is called. */
        view.updateBtn.setOnClickListener {
            updateItem()
        }

        /* If the user clicks the delete Button then deleteItem function is called. */
        view.deleteBtn.setOnClickListener {
            deleteItem()
        }

        /* If the user clicks the pick Button then an new Intent is setup to send this information to OrderActivity. */
        view.pickBtn.setOnClickListener {

            val name = updateTextName.text.toString()  /* Getting the ediText Name, converting to a String and storing it as name. Changing all the String types. */
            val number = updateTextNumber.text.toString() /* Getting the ediText Address, converting to a String and storing it as address. */
            val eircode = updatePostalAddress.text.toString() /* Getting the ediText eircode, converting to a String and storing it as eircode. */
            val address = updateTextAddress.text.toString() /* Getting the ediText mobile, converting to a String and storing it as mobile. */

            /* Creating an intent that when called will go to OrderActivity.kt. */
            val intent = Intent(getActivity(), OrderActivity::class.java)
            intent.putExtra("customerName", name) /* Adding name as extra data. */
            intent.putExtra("customerNumber", number) /* Adding number as extra data. */
            intent.putExtra("postalCode", eircode) /* Adding eircode as extra data. */
            intent.putExtra("address", address) /* Adding address as extra data. */
            requireActivity().startActivity(intent) /* Starting the intent. */
        }

        return view /* Returning the view */
    }

    /* Update Function updates the data of the user in the Database. */
    private fun updateItem() {

        val name = updateTextName.text.toString()  /* Getting the ediText Name, converting to a String and storing it as name. Changing all the String types. */
        val number = updateTextNumber.text.toString() /* Getting the ediText Address, converting to a String and storing it as address. */
        val postalCode = updatePostalAddress.text.toString()  /* Getting the ediText eircode, converting to a String and storing it as eircode. */
        val address = updateTextAddress.text.toString() /* Getting the ediText mobile, converting to a String and storing it as mobile. */

        /* Storing the customer in val customer by passing the current customer's id and updated data. */
        val customer = Customer(args.currentCustomer.id, name,address,postalCode,number)

        posViewModel.updateCustomer(customer) /* Using the ViewModel's function updateCustomer and passing the Customer Object. */
        /* Releasing Toast Message to notify that the customer has been successfully added. */
        Toast.makeText(requireContext(), "$name Successfully updated!", Toast.LENGTH_SHORT).show()

        /* Returning to the List Fragment. */
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)

    }

    /* Delete Function deletes the data of the user in the Database. */
    private fun deleteItem() {

        /* This Alert Dialog double checks with the User if they are sure. */
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete Customer") /* Setting title. */
        builder.setMessage("\n\nAre you sure you want to delete ${args.currentCustomer.name}")
        builder.setPositiveButton("Yes") { _, _ -> /* If the user click yes. */
            posViewModel.deleteCustomer(args.currentCustomer)  /* Using the ViewModel's function deleteCustomer and passing the Customer Object. */
            Toast.makeText(requireContext(), "${args.currentCustomer.name} Successfully updated!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)  /* Returning to the List Fragment. */
        }

        builder.setNegativeButton("No") { _, _ -> } /* If the user click no, return. */
        builder.setTitle("Delete ${args.currentCustomer.name}?")
        builder.create()
        builder.show()
        AlertDialogLayout.TEXT_ALIGNMENT_CENTER /* Text sits in the middle of the box. */
    }

/* Printing onStart() to Logcat. */
override fun onStart() {
    super.onStart()
    Log.i("", "OnStart: UpdateFragment.\n")
}

/* Printing onResume() to Logcat. */
override fun onResume() {
    super.onResume()
    Log.i("", "OnResume: UpdateFragment.\n")
}

/* Printing onPause() to Logcat. */
override fun onPause() {
    super.onPause()
    Log.i("", "OnPause: UpdateFragment.\n")
}

/* Printing onStop() to Logcat. */
override fun onStop() {
    super.onStop()
    Log.i("", "OnStop: UpdateFragment.\n")
}

/* Printing onDestroy() to Logcat. */
override fun onDestroy() {
    super.onDestroy()
    Log.i("", "OnDestroy: UpdateFragment.\n")
}

} /* Ending Fragment. */
