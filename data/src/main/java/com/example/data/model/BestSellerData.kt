package com.example.data.model;

import com.example.domain.sales.BestSeller
import com.google.gson.annotations.SerializedName

data class BestSellerData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_favorites")
    val isFavorites: Boolean,
    @SerializedName("title")
    val title: String,
    @SerializedName("price_without_discount")
    val priceWithoutDiscount: Int,
    @SerializedName("discount_price")
    val discountPrice: Int,
    @SerializedName("picture")
    val picture: String
)

internal fun List<BestSellerData>.toBestSeller(): List<BestSeller> {
    return this.map {
        BestSeller(
            id = it.id,
            isFavorites = it.isFavorites,
            title = it.title,
            priceWithoutDiscount = it.priceWithoutDiscount,
            discountPrice = it.discountPrice,
            picture = it.picture
        )
    }
}
