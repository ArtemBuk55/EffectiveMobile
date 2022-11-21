package com.example.domain

import com.example.domain.cart.Cart
import com.example.domain.details.Details
import com.example.domain.sales.Sales

interface ServerApi {

    suspend fun getSales(): Sales

    suspend fun getDetails(): Details

    suspend fun getCart(): Cart
}