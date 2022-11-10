package com.example.effectivemobile.ui.items

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CartResponse(
    val basketList: MutableList<BasketItem> = mutableListOf(),
    var delivery: String = "",
    var id: Int = -1,
    var total: Int = -1,
) : Parcelable