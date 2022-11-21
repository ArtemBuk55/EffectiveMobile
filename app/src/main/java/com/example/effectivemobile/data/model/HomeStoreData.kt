package com.example.effectivemobile.data.model;

import com.example.effectivemobile.domain.sales.HotSales
import com.google.gson.annotations.SerializedName

data class HomeStoreData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("title")
    val title: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("picture")
    val picture: String,
    @SerializedName("is_buy")
    val isBuy: Boolean
)

internal fun List<HomeStoreData>.toHotSales(): List<HotSales> {
    return this.map {
        HotSales(
            id = it.id,
            isNew = it.isNew,
            title = it.title,
            subtitle = it.subtitle,
            picture = it.picture,
            isBuy = it.isBuy
        )
    }
}