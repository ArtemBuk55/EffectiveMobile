package com.example.effectivemobile.presentation.ui.cart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.ActivityCartBinding
import com.example.effectivemobile.domain.cart.Basket
import com.example.effectivemobile.domain.cart.Cart
import com.example.effectivemobile.presentation.ui.cart.viewmodel.CartViewModel
import com.example.effectivemobile.presentation.utils.cornerRadius

class CartActivity : AppCompatActivity() {

    private val TAG = "CartActivity"

    private lateinit var binding: ActivityCartBinding
    private lateinit var cartResponse: Cart
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
        window.navigationBarColor = resources.getColor(R.color.titles_text_color)
        binding.imageBack.setOnClickListener { finish() }
        binding.btnCheckout.run {
            cornerRadius(20f)
            setOnClickListener {
                //checkout
            }
        }
        binding.cartContainer.cornerRadius(75f, false)
        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]
        startObservers()
        cartViewModel.launchSearch()
        initAdapter()
    }

    private fun startObservers() {
        cartViewModel.cart.observe(this) {
            cartResponse = it
            initViews()
            basketAdapter.addBasketList(cartResponse.basketList.toBasketItems())
        }
    }

    private fun initViews() = binding.apply {
        tvTotalPrice.text = this@CartActivity.getString(
            R.string.dollars_us,
            cartResponse.total.toString()
        )
        tvDeliveryPrice.text = cartResponse.delivery
    }

    private fun initAdapter() = binding.apply {
        basketAdapter = BasketAdapter(changeTotalPriceCallBack)
        rvBasket.adapter = basketAdapter
    }
}

private fun List<Basket>.toBasketItems(): List<BasketItem> {
    return this.map {
        BasketItem(
            id = it.id,
            price = it.price,
            images = it.images,
            title = it.title
        )
    }
}
