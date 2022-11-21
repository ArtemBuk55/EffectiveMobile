package com.example.effectivemobile.domain.cart

data class Cart(
    val basketList: List<Basket> = emptyList(),
    val delivery: String,
    val id: Int,
    var total: Int
)