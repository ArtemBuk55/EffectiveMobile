package com.example.effectivemobile.domain.details

import com.example.domain.details.Details
import com.example.domain.ServerApi

class GetDetailsInteractor(private val serverApi: ServerApi) {

    suspend fun execute(): Details {
        return serverApi.getDetails()
    }
}