package com.example.effectivemobile.network.model

import com.google.gson.annotations.SerializedName

data class GetCartRequest(
    @SerializedName("basket")
    val basketList: List<BasketData> = emptyList(),
    @SerializedName("delivery")
    val delivery: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("total")
    var total: Int
)
