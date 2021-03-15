package com.example.pos.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pos.model.Customer
import com.example.pos.model.Order

/* Contains the methods used for accessing the database. */
@Dao /* Data Access Object. */
interface POSDao {

    /* Query that if a User attempts to insert an identical customer object then the one currently stored will be replaced by the new one. */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCustomer(customer: Customer)

    /* Query to collect all the Customers and in the order of their names descending. */
    @Query("SELECT * FROM customer_table ORDER BY name ASC")
    fun readAllData(): LiveData<List<Customer>>

    /* Query to delete all the Customers. */
    @Query("DELETE FROM customer_table")
    suspend fun deleteAll()

    /* Query to delete a Customer, passing a instance of the Customer Object. */
    @Delete
    suspend fun deleteCustomer(customer: Customer)

    /* Query to update a Customer, passing a instance of the Customer Object. */
    @Update
    suspend fun updateCustomer(customer: Customer)

    /* Query to search the Database where letters or numbers match to name, address or mobile of a customer. */
    @Query("SELECT * FROM customer_table WHERE name LIKE :searchQuery OR address LIKE :searchQuery OR mobile LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<Customer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) /*If there is a duplicate of an order then replace the previous. */
    suspend fun addOrder(order: Order)

    /* Query to collect all the Orders and in the order of their id descending. */
    @Query("SELECT * FROM order_table ORDER BY id ASC")
    fun readAllOrder(): LiveData<List<Order>>
} /* Ending Kotlin File. */