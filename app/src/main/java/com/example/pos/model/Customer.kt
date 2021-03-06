package com.example.pos.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "customer_table") /* Table name specified. */
data class Customer(
    @PrimaryKey(autoGenerate = true)  /* Automatically generate numbers for ID Column. */
    val id: Int, /* Primary Key. */
    val name: String, /* Field. */
    val address: String,
    val eircode: String,
    val mobile: String
) : Parcelable /*Allows us to pass customer info as arguments so it can be updated. */


/* An example of the Format of the Customer Table is:
id      name                address              eircode     mobile
1       Declan Thorne       123 Fake Street      12Q AS34    0876542121
                            Long Road
                            Dublin
 */