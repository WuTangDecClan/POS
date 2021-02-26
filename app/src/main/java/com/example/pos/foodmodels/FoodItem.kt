package com.example.pos.foodmodels

/* Object for modeling data in the recycler view. */
/* No need for Getters and Setters with Kotlin, adding properties to Food Item Object. */
data class FoodItem(
    var productQuantity: String,
    var productName: String,
    var productCost: String )