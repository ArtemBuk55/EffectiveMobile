package com.example.effectivemobile.network.model

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
