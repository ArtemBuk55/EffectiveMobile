package com.example.effectivemobile.domain.cart

import com.example.domain.ServerApi
import com.example.domain.cart.Cart

class GetCartInteractor(private val serverApi: ServerApi) {

    suspend fun execute(): Cart {
        return serverApi.getCart()
    }
}