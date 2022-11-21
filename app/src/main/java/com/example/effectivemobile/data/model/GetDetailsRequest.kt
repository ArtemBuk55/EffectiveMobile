package com.example.effectivemobile.data.model

import com.example.effectivemobile.domain.details.Details
import com.google.gson.annotations.SerializedName

data class GetDetailsRequest(
    @SerializedName("CPU")
    val CPU: String,
    @SerializedName("camera")
    val camera: String,
    @SerializedName("capacity")
    val capacity: List<Int> = emptyList(),
    @SerializedName("color")
    val color: List<String> = emptyList(),
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<String> = emptyList(),
    @SerializedName("isFavorites")
    var isFavorites: Boolean,
    @SerializedName("price")
    val price: Int,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("sd")
    val sd: String,
    @SerializedName("ssd")
    val ssd: String,
    @SerializedName("title")
    val title: String
)

internal fun GetDetailsRequest.toDetails(): Details {
    return Details(
        cpu = this.CPU,
        camera = camera,
        capacity = capacity,
        color = color,
        id = id,
        images = images,
        isFavorites = isFavorites,
        price = price,
        rating = rating,
        sd = sd,
        ssd = ssd,
        title = title
    )
}