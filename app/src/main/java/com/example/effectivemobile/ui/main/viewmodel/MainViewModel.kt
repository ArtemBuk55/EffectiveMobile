package com.example.effectivemobile.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobile.network.ServerRepository
import com.example.effectivemobile.network.model.GetSalesRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val repository = ServerRepository()

    val sales: MutableLiveData<GetSalesRequest> = MutableLiveData()

    fun launchSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val sales = repository.getSales()
                this@MainViewModel.sales.postValue(sales)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}