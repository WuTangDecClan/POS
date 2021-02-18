package com.example.pos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.pos.repository.POSRepository
import com.example.pos.data.POSRoomDatabase
import com.example.pos.model.Customer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
/* Accesses all the Queries from the DAO. */
class POSViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Customer>>
    private val repository: POSRepository

    init { /* Always executed first when POSVIEWMODEL is called. */
        val POSDao = POSRoomDatabase.getDatabase(
            application
        ).POSDao()
        repository = POSRepository(POSDao)
        readAllData = repository.readAllData
    }

    fun addCustomer(customer: Customer) { /* Dispatchers.IO says we want to run this in a background thread. */
        viewModelScope.launch(Dispatchers.IO) { /* Runs in worker thread, or background thread. */
            repository.addCustomer(customer)
        }
    }

    fun updateCustomer(customer: Customer) {
        viewModelScope.launch(Dispatchers.IO){ /* Going to run this Query from a background thread. */
            repository.updateCustomer(customer)
        }
    }

    fun deleteCustomer(customer: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCustomer(customer)
        }
    }
}