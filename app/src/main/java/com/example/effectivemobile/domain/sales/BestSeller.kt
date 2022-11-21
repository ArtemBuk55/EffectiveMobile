package com.example.effectivemobile.domain.sales

data class BestSeller(
    val id: Int,
    val isFavorites: Boolean,
    val title: String,
    val priceWithoutDiscount: Int,
    val discountPrice: Int,
    val picture: String
)