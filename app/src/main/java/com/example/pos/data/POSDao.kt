package com.example.pos.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pos.model.Customer
import com.example.pos.model.Order

/* Contains the methods used for accessing the database. */
@Dao /* Data Access Object. */
interface POSDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) /*If there is a duplicate then replace the previous. */
    suspend fun addCustomer(customer: Customer)

    @Query("SELECT * FROM customer_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Customer>>

    @Query("DELETE FROM customer_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteCustomer(customer: Customer)

    @Update
    suspend fun updateCustomer(customer: Customer)

    @Query("SELECT * FROM customer_table WHERE name LIKE :searchQuery OR address LIKE :searchQuery OR mobile LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<Customer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrder(order: Order)

    @Query("SELECT * FROM order_table ORDER BY id ASC")
    fun readAllOrder(): LiveData<List<Order>>
}