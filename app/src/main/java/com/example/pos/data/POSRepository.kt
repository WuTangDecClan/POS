package com.example.pos.data

import androidx.lifecycle.LiveData

/* A class that abstracts access to multiple data sources. */
// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class POSRepository(private val POSDao: POSDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val readAllData: LiveData<List<Customer>> = POSDao.readAllData()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.

    suspend fun addCustomer(customer: Customer) {
        POSDao.addCustomer(customer) /*Accessing from User Dao. */
    }
}