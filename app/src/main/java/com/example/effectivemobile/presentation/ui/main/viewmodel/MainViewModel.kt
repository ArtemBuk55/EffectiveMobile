package com.example.effectivemobile.presentation.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ServerRepositoryApi
import com.example.data.ServerRepositoryImpl
import com.example.effectivemobile.domain.sales.GetSalesInteractor
import com.example.domain.sales.Sales
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository: com.example.data.ServerRepositoryApi =
        com.example.data.ServerRepositoryImpl()
    private val getSalesInteractor: GetSalesInteractor =
        GetSalesInteractor(repository)
    val sales: MutableLiveData<com.example.domain.sales.Sales> = MutableLiveData()

    fun launchSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val sales = getSalesInteractor.execute()
                this@MainViewModel.sales.postValue(sales)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}