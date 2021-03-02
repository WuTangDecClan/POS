package com.example.pos

data class DataModel(val itemName: String, val itemQuantity: String, val itemPrice: String, val topping1: String? = null,  val topping2: String? = null,  val topping3: String? = null,  val topping4: String? = null, val viewType: Int)