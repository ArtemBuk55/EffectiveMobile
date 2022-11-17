package com.example.effectivemobile.network

import com.example.effectivemobile.network.model.GetCartRequest
import com.example.effectivemobile.network.model.GetDetailsRequest
import com.example.effectivemobile.network.model.GetSalesRequest
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.withTimeout
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerRepository {

    private val BASE_URL = "https://run.mocky.io"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val mockyApiSource = retrofit
        .create(MockyApiSource::class.java)

    suspend fun getSales(): GetSalesRequest? {
        val sales = withTimeout(15000L) {
            mockyApiSource.getSalesAsync().await()
        }
        return sales
    }

    suspend fun getDetails(): GetDetailsRequest? {
        val details = withTimeout(15000L) {
            mockyApiSource.getDetailsAsync().await()
        }
        return details
    }

    suspend fun getCart(): GetCartRequest? {
        val cart = withTimeout(15000L) {
            mockyApiSource.getCartAsync().await()
        }
        return cart
    }
}
