package com.example.effectivemobile.network.model;

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