package com.example.data

import com.example.data.model.*
import com.example.data.model.toDetails
import com.example.domain.cart.Cart
import com.example.domain.details.Details
import com.example.domain.sales.Sales
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.withTimeout
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerRepositoryImpl: ServerRepositoryApi { // TODO: make internal after setting DI

    private val BASE_URL = "https://run.mocky.io"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    internal val mockyApiSource = retrofit
        .create(MockyApiSource::class.java)

    override suspend fun getSales(): Sales {
        val salesData = withTimeout(15000L) {
            mockyApiSource.getSalesAsync().await()
        }
        return salesData?.toSales()!! // TODO: replace force unwrap with custom Exceptions or states
    }

    override suspend fun getDetails(): Details {
        val detailsData = withTimeout(15000L) {
            mockyApiSource.getDetailsAsync().await()
        }
        return detailsData?.toDetails()!!  // TODO: replace force unwrap with custom Exceptions or states
    }

    override suspend fun getCart(): Cart {
        val cartData = withTimeout(15000L) {
            mockyApiSource.getCartAsync().await()
        }
        return cartData?.toCart()!!  // TODO: replace force unwrap with custom Exceptions or states
    }

}