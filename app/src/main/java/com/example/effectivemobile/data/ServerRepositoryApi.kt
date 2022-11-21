package com.example.effectivemobile.data

import com.example.effectivemobile.data.model.GetCartRequest
import com.example.effectivemobile.data.model.GetDetailsRequest
import com.example.effectivemobile.data.model.GetSalesRequest

interface ServerRepositoryApi {

    suspend fun getSalesData(): GetSalesRequest

    suspend fun getDetailsData(): GetDetailsRequest

    suspend fun getCartData(): GetCartRequest
}