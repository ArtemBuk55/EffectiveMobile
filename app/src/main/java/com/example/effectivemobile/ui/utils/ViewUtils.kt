package com.example.effectivemobile.ui.utils

import android.view.View

fun View.cornerRadius(radius: Float, bottomCorners: Boolean = true, topCorners: Boolean = true) {
    clipToOutline = true
    outlineProvider = MyViewRoundRectOutlineProvider(radius, bottomCorners, topCorners)
}

fun View.showView(){
    visibility = View.VISIBLE
}

fun View.hideView(){
    visibility = View.GONE
}

