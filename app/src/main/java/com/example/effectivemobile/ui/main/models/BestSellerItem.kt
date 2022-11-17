package com.example.effectivemobile.ui.main.models

data class BestSellerItem(
    var id: Int = -1,
    var isFavorites: Boolean = false,
    var title: String = "",
    var priceWithoutDiscount: Int = -1,
    var discountPrice: Int = -1,
    var pictureURL: String = ""
)