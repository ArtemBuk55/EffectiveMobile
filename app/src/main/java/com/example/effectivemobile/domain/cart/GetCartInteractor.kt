package com.example.effectivemobile.domain.cart

import com.example.effectivemobile.data.ServerRepositoryApi
import com.example.effectivemobile.data.model.toCart

class GetCartInteractor(private val repositoryApi: ServerRepositoryApi) {

    suspend fun execute(): Cart {
        return repositoryApi.getCartData().toCart()
    }
}