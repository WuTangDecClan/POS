package com.example.pos

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pos.R
import com.example.pos.model.Customer
import com.example.pos.viewmodel.POSViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class addFragment : Fragment() {

    private lateinit var _posViewModel: POSViewModel /* Storing the View Model in variable posViewModel. */
    private lateinit var database:FirebaseDatabase
    private lateinit var reference: DatabaseReference

    /* Access a Cloud Firestore instance from the Activity. */
    val db = Firebase.firestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /* Inflate the layout for this fragment. */
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        /* Creates ViewModelProvider. This will create ViewModels and retain them in a store of the given ViewModelStoreOwner. */
        _posViewModel = ViewModelProvider(this).get(POSViewModel::class.java)


        database = FirebaseDatabase.getInstance("https://pos-system-317c9-default-rtdb.europe-west1.firebasedatabase.app/")
        reference = database.getReference("Users")

        /* If the user clicks on the add button. */
        view.add_btn.setOnClickListener {
            insertDataToDatabase()  /* Method to insert the data into the database is called. */
        }

        return view /* Returning the view */
    }

    private fun insertDataToDatabase() { /* Getting the texts and adding them to the Customer Database */
        val name = editTextName.text.toString() /* Getting the ediText Name, converting to a String and storing it as name. */
        val address = editTextTextAddress.text.toString() /* Getting the ediText Address, converting to a String and storing it as address. */
        val eircode = editTextPostalAddress.text.toString() /* Getting the ediText eircode, converting to a String and storing it as eircode. */
        val mobile = editTextNumber.text.toString() /* Getting the ediText mobile, converting to a String and storing it as mobile. */

        var id = reference.push().key
        /* Creating a Customer Object. Using all the stored variables containing user imputed information and storing in val Customer an instance of Customer Object. */
        val customer = Customer(0, name, address, eircode, mobile)
        /* Adding Customer to the Room Database. */
        _posViewModel.addCustomer(customer)
        /* Releasing Toast Message to notify that the customer has been successfully added. */

        /* Creating a new Customer.  */
        val cust = hashMapOf(
            "name" to name,
            "number" to mobile,
            "eircode" to eircode,
            "address" to address
        )

        /* Add a new document with a generated ID. */
        db.collection("customers")
            .add(cust)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

        Toast.makeText(requireContext(), "$name $id was Successfully added!", Toast.LENGTH_SHORT).show()

        /* Returning to the List Fragment. */
        findNavController().navigate(R.id.action_addFragment_to_listFragment)

    }

    /* Printing onStart() to Logcat. */
    override fun onStart() {
        super.onStart()
        Log.i("", "OnStart: AddFragment.\n")
    }

    /* Printing onResume() to Logcat. */
    override fun onResume() {
        super.onResume()
        Log.i("", "OnResume: AddFragment.\n")
    }

    /* Printing onPause() to Logcat. */
    override fun onPause() {
        super.onPause()
        Log.i("", "OnPause: AddFragment.\n")
    }

    /* Printing onStop() to Logcat. */
    override fun onStop() {
        super.onStop()
        Log.i("", "OnStop: AddFragment.\n")
    }

    /* Printing onDestroy() to Logcat. */
    override fun onDestroy() {
        super.onDestroy()
        Log.i("", "OnDestroy: AddFragment.\n")
    }

} /* Ending Fragment. */
