package com.example.effectivemobile.domain.sales

import com.example.effectivemobile.data.ServerRepositoryApi
import com.example.effectivemobile.data.model.toSales

class GetSalesInteractor(private val repositoryApi: ServerRepositoryApi) {

    suspend fun execute(): Sales {
        return repositoryApi.getSalesData().toSales()
    }
}
