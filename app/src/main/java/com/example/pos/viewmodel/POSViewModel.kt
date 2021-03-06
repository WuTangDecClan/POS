package com.example.pos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.pos.repository.POSRepository
import com.example.pos.data.POSRoomDatabase
import com.example.pos.model.Customer
import com.example.pos.model.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/* Accesses all the Queries from the DAO. ViewModel provides data to the User Interface.
   The ViewModel is the middle man between the Repository and the User Interface. */

class POSViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Customer>>
    val readAllOrder: LiveData<List<Order>>

    private val repository: POSRepository

    init { /* Always executed first when POSVIEWMODEL is called. */
        val POSDao = POSRoomDatabase.getDatabase(
            application
        ).POSDao()
        repository = POSRepository(POSDao)
        readAllData = repository.readAllData
        readAllOrder = repository.readAllOrder
    }

    fun addCustomer(customer: Customer) { /* Function to add Customer to Repository. */
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCustomer(customer)
        }
    }

    fun addOrder(order: Order) { /* Dispatchers.IO says we want to run this in a background thread. */
        viewModelScope.launch(Dispatchers.IO) { /* Runs in worker thread, or background thread. */
            repository.addOrder(order)
        }
    }

    fun updateCustomer(customer: Customer) { /* Function to update Customer in the Repository. */
        viewModelScope.launch(Dispatchers.IO){ /* Going to run this Query from a background thread. */
            repository.updateCustomer(customer)
        }
    }

    fun deleteCustomer(customer: Customer) { /* Function to delete Customer in the Repository. */
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCustomer(customer)
        }
    }
}