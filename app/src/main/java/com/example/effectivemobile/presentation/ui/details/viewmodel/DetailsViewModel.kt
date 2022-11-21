package com.example.effectivemobile.presentation.ui.details.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobile.data.ServerRepositoryApi
import com.example.effectivemobile.data.ServerRepositoryImpl
import com.example.effectivemobile.domain.details.Details
import com.example.effectivemobile.domain.details.GetDetailsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val repository: ServerRepositoryApi = ServerRepositoryImpl()
    private val getDetailsInteractor: GetDetailsInteractor = GetDetailsInteractor(repository)

    val details: MutableLiveData<Details> = MutableLiveData()

    fun launchSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val details = getDetailsInteractor.execute()
                this@DetailsViewModel.details.postValue(details)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}