package com.example.effectivemobile.domain.sales

data class HotSales(
    val id: Int,
    val isNew: Boolean,
    val title: String,
    val subtitle: String,
    val picture: String,
    val isBuy: Boolean
)