package com.example.effectivemobile.ui.items

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BasketItem(
    var id: Int = -1,
    var pictureURL: String = "",
    var price: Int = -1,
    var title: String = ""
    ) : Parcelable