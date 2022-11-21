package com.example.effectivemobile.data

import com.example.effectivemobile.data.model.GetCartRequest
import com.example.effectivemobile.data.model.GetDetailsRequest
import com.example.effectivemobile.data.model.GetSalesRequest
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.withTimeout
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerRepositoryImpl : ServerRepositoryApi {

    private val BASE_URL = "https://run.mocky.io"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val mockyApiSource = retrofit
        .create(MockyApiSource::class.java)

    override suspend fun getSalesData(): GetSalesRequest {
        val sales = withTimeout(15000L) {
            mockyApiSource.getSalesAsync().await()
        }
        return sales!!
    }

    override suspend fun getDetailsData(): GetDetailsRequest {
        val details = withTimeout(15000L) {
            mockyApiSource.getDetailsAsync().await()
        }
        return details!!
    }

    override suspend fun getCartData(): GetCartRequest {
        val cart = withTimeout(15000L) {
            mockyApiSource.getCartAsync().await()
        }
        return cart!!
    }
}
