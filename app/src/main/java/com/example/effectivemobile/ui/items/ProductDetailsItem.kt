package com.example.effectivemobile.ui.items

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductDetailsItem(
    var CPU: String = "",
    var camera: String = "",
    var capacity: MutableList<Int> = mutableListOf(),  //нет
    var color: MutableList<String> = mutableListOf(),//нет
    var id: Int = -1, //no
    var images: MutableList<String> = mutableListOf(),//no
    var isFavorites: Boolean = false,
    var price: Int = -1,
    var rating: Double = -1.0,//доделать
    var sd: String = "",
    var ssd: String = "",
    var title: String = ""
) : Parcelable