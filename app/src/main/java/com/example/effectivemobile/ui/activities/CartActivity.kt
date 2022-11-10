package com.example.effectivemobile.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.ActivityCartBinding
import com.example.effectivemobile.ui.adapters.CartAdapter
import com.example.effectivemobile.ui.items.CartResponse
import com.example.effectivemobile.ui.utils.cornerRadius

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var cartResponse: CartResponse
    private lateinit var cartAdapter: CartAdapter
    private val changeTotalPriceCallBack = { changeOn: Int ->
        cartResponse.total += changeOn
        binding.tvTotalPrice.text = this.getString(
            R.string.dollars_us,
            cartResponse.total.toString()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater).also { setContentView(it.root) }
        cartResponse = intent.getParcelableExtra(ARG_CART)!!
        initViews()
        initAdapter()
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
        cartAdapter = CartAdapter(changeTotalPriceCallBack)
        rvBasket.adapter = cartAdapter
        cartAdapter.addBasketList(cartResponse.basketList)
    }

    companion object {
        const val ARG_CART = "ARG_CART"
    }
}