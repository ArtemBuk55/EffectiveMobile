package com.example.effectivemobile.presentation.ui.cart.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ServerRepositoryApi
import com.example.data.ServerRepositoryImpl
import com.example.domain.cart.Cart
import com.example.effectivemobile.domain.cart.GetCartInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {

    private val repository: com.example.data.ServerRepositoryApi =
        com.example.data.ServerRepositoryImpl()
    private val getCartInteractor: GetCartInteractor = GetCartInteractor(repository)

    val cart: MutableLiveData<com.example.domain.cart.Cart> = MutableLiveData()

    fun launchSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val cart = getCartInteractor.execute()
                this@CartViewModel.cart.postValue(cart)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}