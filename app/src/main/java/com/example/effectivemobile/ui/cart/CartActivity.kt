package com.example.effectivemobile.ui.cart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.ActivityCartBinding
import com.example.effectivemobile.network.model.GetCartRequest
import com.example.effectivemobile.ui.cart.viewmodel.CartViewModel
import com.example.effectivemobile.utils.cornerRadius

class CartActivity : AppCompatActivity() {

    private val TAG = "CartActivity"

    private lateinit var binding: ActivityCartBinding
    private lateinit var cartResponse: GetCartRequest
    private lateinit var basketAdapter: BasketAdapter
    private lateinit var cartViewModel: CartViewModel

    private val changeTotalPriceCallBack = { changeOn: Int ->
        cartResponse.total = cartResponse.total.plus(changeOn)
        binding.tvTotalPrice.text = this.getString(
            R.string.dollars_us,
            cartResponse.total.toString()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater).also { setContentView(it.root) }
        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]
        startObservers()
        cartViewModel.launchSearch()
        initAdapter()
    }

    private fun startObservers() {
        cartViewModel.cart.observe(this) {
            cartResponse = it
            initViews()
            basketAdapter.addBasketList(cartResponse.basketList)
        }
    }

    private fun initViews() = binding.apply {
        tvTotalPrice.text = this@CartActivity.getString(
            R.string.dollars_us,
            cartResponse.total.toString()
        )
        tvDeliveryPrice.text = cartResponse.delivery
        imageBack.setOnClickListener { finish() }
        btnCheckout.run {
            cornerRadius(20f)
            setOnClickListener {
                //checkout
            }
        }
        cartContainer.cornerRadius(75f, false)
    }

    private fun initAdapter() = binding.apply {
        basketAdapter = BasketAdapter(changeTotalPriceCallBack)
        rvBasket.adapter = basketAdapter
    }
}