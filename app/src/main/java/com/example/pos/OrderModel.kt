package com.example.pos

/* Object for modeling data in the recycler view. */
/* No need for Getters and Setters with Kotlin, adding properties to Food Item Object. */
data class OrderModel(
    var productQuantity: String,
    var productName: String,
    var productCost: String,
    var additional1: String,
    var additional2: String,
    var additional3: String,
    var additional4: String)