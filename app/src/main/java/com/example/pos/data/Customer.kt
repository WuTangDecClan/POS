package com.example.pos.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer_table")
data class Customer (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val address1: String,
    val address2: String,
    val address3: String,
    val eircode: String,
    val mobile: Int,
    val note: String
)