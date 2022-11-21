package com.example.effectivemobile.data

import com.example.effectivemobile.data.model.GetCartRequest
import com.example.effectivemobile.data.model.GetDetailsRequest
import com.example.effectivemobile.data.model.GetSalesRequest
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface MockyApiSource {

    @GET("/v3/654bd15e-b121-49ba-a588-960956b15175")
    fun getSalesAsync(): Deferred<GetSalesRequest?>

    @GET("/v3/6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    fun getDetailsAsync(): Deferred<GetDetailsRequest?>

    @GET("/v3/53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    fun getCartAsync(): Deferred<GetCartRequest?>
}