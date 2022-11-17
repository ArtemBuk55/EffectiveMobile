package com.example.effectivemobile.network.model;

import com.google.gson.annotations.SerializedName

data class GetSalesRequest(
    @SerializedName("home_store")
    val homeStoreData: List<HomeStoreData> = emptyList(),
    @SerializedName("best_seller")
    val bestSellerData: List<BestSellerData> = emptyList()
)