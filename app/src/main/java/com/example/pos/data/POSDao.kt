package com.example.pos.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/* Contains the methods used for accessing the database. */
@Dao
interface POSDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCustomer(customer: Customer)

    @Query("SELECT * FROM customer_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Customer>>

    @Query("DELETE FROM customer_table")
    suspend fun deleteAll()
}