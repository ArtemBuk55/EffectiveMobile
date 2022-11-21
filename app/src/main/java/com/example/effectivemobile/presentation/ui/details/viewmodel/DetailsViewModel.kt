package com.example.effectivemobile.presentation.ui.details.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ServerRepositoryApi
import com.example.data.ServerRepositoryImpl
import com.example.domain.details.Details
import com.example.effectivemobile.domain.details.GetDetailsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val repository: com.example.data.ServerRepositoryApi =
        com.example.data.ServerRepositoryImpl()
    private val getDetailsInteractor: GetDetailsInteractor = GetDetailsInteractor(repository)

    val details: MutableLiveData<com.example.domain.details.Details> = MutableLiveData()

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