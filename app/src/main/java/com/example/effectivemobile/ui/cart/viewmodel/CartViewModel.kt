package com.example.effectivemobile.ui.cart.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobile.network.ServerRepository
import com.example.effectivemobile.network.model.GetCartRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {

    val repository = ServerRepository()

    val cart: MutableLiveData<GetCartRequest> = MutableLiveData()

    fun launchSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val cart = repository.getCart()
                this@CartViewModel.cart.postValue(cart)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}