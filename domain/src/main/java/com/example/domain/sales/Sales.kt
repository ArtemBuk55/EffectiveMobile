package com.example.domain.sales


data class Sales(
    val hotSales: List<HotSales> = emptyList(),
    val bestSeller: List<BestSeller> = emptyList()
)