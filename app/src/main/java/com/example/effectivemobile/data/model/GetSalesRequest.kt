package com.example.effectivemobile.data.model;

import com.example.effectivemobile.domain.sales.Sales
import com.google.gson.annotations.SerializedName

data class GetSalesRequest(
    @SerializedName("home_store")
    val homeStoreData: List<HomeStoreData> = emptyList(),
    @SerializedName("best_seller")
    val bestSellerData: List<BestSellerData> = emptyList()
)

internal fun GetSalesRequest.toSales(): Sales {
    return Sales(
        hotSales = homeStoreData.toHotSales(),
        bestSeller = bestSellerData.toBestSeller()
    )
}
