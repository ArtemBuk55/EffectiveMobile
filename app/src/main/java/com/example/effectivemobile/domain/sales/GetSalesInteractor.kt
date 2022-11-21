package com.example.effectivemobile.domain.sales

import com.example.domain.sales.Sales
import com.example.domain.ServerApi

class GetSalesInteractor(private val serverApi: ServerApi) {

    suspend fun execute(): Sales {
        return serverApi.getSales()
    }
}
