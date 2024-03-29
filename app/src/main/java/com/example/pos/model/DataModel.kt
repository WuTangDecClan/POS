package com.example.pos.model

data class DataModel(
                     val itemName: String? = null,
                     val itemQuantity: String? = null,
                     val itemPrice: String? = null,
                     val topping1: String? = null,
                     val topping2: String? = null,
                     val topping3: String? = null,
                     val topping4: String? = null,
                     val topping5: String? = null,
                     val delivery: String? = null,
                     val sideName: String? = null,
                     val sidePrice: String? = null,
                     val openFoodDetails: String? = null,
                     val openFoodCharge: String? = null,
                     val customerName: String? = null,
                     val customerNumber: String? = null,
                     val customerPostal: String? = null,
                     val customerAddress: String? = null,
                     val paymentAmount: String? = null,
                     val paymentType: String? = null,
                     val viewType: Int)