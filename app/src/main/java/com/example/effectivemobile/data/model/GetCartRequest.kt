package com.example.effectivemobile.data.model

import com.example.effectivemobile.domain.cart.Basket
import com.example.effectivemobile.domain.cart.Cart
import com.google.gson.annotations.SerializedName

data class GetCartRequest(
    @SerializedName("basket")
    val basketDataList: List<BasketData> = emptyList(),
    @SerializedName("delivery")
    val delivery: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("total")
    var total: Int
)

internal fun GetCartRequest.toCart(): Cart {
    return Cart(
        basketList = basketDataList.toBasketList(),
        delivery = delivery,
        id = id,
        total = total
    )
}

private fun List<BasketData>.toBasketList(): List<Basket> {
    return this.map {
        Basket(
            id = it.id,
            images = it.images,
            price = it.price,
            title = it.title
        )
    }
}