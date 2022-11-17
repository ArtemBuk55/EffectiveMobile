package com.example.effectivemobile.ui.details.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobile.network.ServerRepository
import com.example.effectivemobile.network.model.GetDetailsRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    val repository = ServerRepository()

    val details: MutableLiveData<GetDetailsRequest> = MutableLiveData()

    fun launchSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val details = repository.getDetails()
                this@DetailsViewModel.details.postValue(details)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}