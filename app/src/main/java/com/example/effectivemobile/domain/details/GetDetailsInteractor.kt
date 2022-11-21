package com.example.effectivemobile.domain.details

import com.example.effectivemobile.data.ServerRepositoryApi
import com.example.effectivemobile.data.model.toDetails

class GetDetailsInteractor(private val repositoryApi: ServerRepositoryApi) {

    suspend fun execute(): Details {
        return repositoryApi.getDetailsData().toDetails()
    }
}