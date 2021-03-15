package com.example.pos.model

data class ActiveOrderModel(
    val customerName: String? = null,
    val customerNumber: String? = null,
    val customerPostal: String? = null,
    val customerAddress: String? = null,
    val paymentAmount: String? = null,
    val paymentType: String? = null )