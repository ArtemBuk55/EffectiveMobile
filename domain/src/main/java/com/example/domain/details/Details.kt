package com.example.domain.details

data class Details(
    val cpu: String,
    val camera: String,
    val capacity: List<Int> = emptyList(),
    val color: List<String> = emptyList(),
    val id: Int,
    val images: List<String> = emptyList(),
    var isFavorites: Boolean,
    val price: Int,
    val rating: Double,
    val sd: String,
    val ssd: String,
    val title: String
)