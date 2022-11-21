package com.example.effectivemobile.data.model

import com.example.effectivemobile.domain.cart.Basket
import com.google.gson.annotations.SerializedName

data class BasketData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("title")
    val title: String
)

internal fun BasketData.toBasket(): Basket {
    return Basket(
        id = id,
        images = images,
        price = price,
        title = title
    )
}
