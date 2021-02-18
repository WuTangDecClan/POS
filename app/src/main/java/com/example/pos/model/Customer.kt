package com.example.pos.model

import android.os.Parcelable
import android.text.Editable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "customer_table")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val address: String,
    val eircode: String,
    val mobile: String
) : Parcelable /*Allows us to pass customer info as arguments so it can be updated. */