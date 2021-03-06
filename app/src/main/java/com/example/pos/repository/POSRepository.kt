package com.example.pos.repository

import androidx.lifecycle.LiveData
import com.example.pos.data.POSDao
import com.example.pos.model.Customer
import com.example.pos.model.Order

/* A class that abstracts access to multiple data sources. */
/* Declares the DAO as a private property in the constructor. Passes in the DAO rather
than the whole database, because you only need access to the DAO */

class POSRepository(private val POSDao: POSDao) {

    /* Room executes all queries on a separate thread.
       Observed Flow will notify the observer when the data has changed. */

    val readAllData: LiveData<List<Customer>> = POSDao.readAllData()  /* Reading all the Data from the Customer Table & below from the Order Table. */
    val readAllOrder: LiveData<List<Order>> = POSDao.readAllOrder()

    /* By default Room runs suspend queries off the main thread, therefore, we don't need to
       implement anything else to ensure we're not doing long running database work off the main thread. */

    /* Adding Customer & Order. */

    suspend fun addCustomer(customer: Customer) {
        POSDao.addCustomer(customer) /*Accessing from User Dao. */
    }

    suspend fun addOrder(order: Order) {
        POSDao.addOrder(order)
    }

    /* Updating Customer. */
    suspend fun updateCustomer(customer: Customer) {
        POSDao.updateCustomer(customer)
    }

    /* Deleting Customer. */
    suspend fun deleteCustomer(customer: Customer) {
        POSDao.deleteCustomer(customer)
    }
}