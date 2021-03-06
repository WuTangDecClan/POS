package com.example.pos.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "order_table", foreignKeys = [ForeignKey(
    entity = Customer::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("customer"),
    onDelete = ForeignKey.CASCADE)])
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Int, /* Primary Key. */
    val name: String, /* Field. */
    val address: String,
    val eircode: String,
    val mobile: String,
    @ColumnInfo(index = true)
    val customer: String
) : Parcelable /*Allows us to pass customer info as arguments so it can be updated. */

/* Embedded Object. */
data class CustomerOrder(
    @Embedded
    val customer: Customer,
    @Relation(
        parentColumn = "id",
        entityColumn = "customer"
    )
    val orders: List<Order>
)