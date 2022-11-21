package com.example.effectivemobile.domain.sales


data class Sales(
    val hotSales: List<HotSales> = emptyList(),
    val bestSeller: List<BestSeller> = emptyList()
)