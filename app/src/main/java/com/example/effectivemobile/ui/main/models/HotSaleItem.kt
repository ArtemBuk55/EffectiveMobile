package com.example.effectivemobile.ui.main.models

data class HotSaleItem(
    var id: Int = -1,
    var isNew: Boolean = false,
    var title: String = "",
    var subtitle: String = "",
    var pictureURL: String = "",
    var isBuy: Boolean = false
)
