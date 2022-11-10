package com.example.effectivemobile.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.ItemBasketBinding
import com.example.effectivemobile.ui.items.BasketItem
import com.example.effectivemobile.ui.utils.cornerRadius

class CartAdapter(private val changeTotalPriceCallBack: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var basketList: MutableList<BasketItem> = mutableListOf()

    fun addBasketList(basketList: MutableList<BasketItem>) {
        this.basketList = basketList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBasketBinding.inflate(layoutInflater, parent, false)
        return BasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BasketViewHolder).onBind(basketList[position])
    }

    override fun getItemCount(): Int {
        return basketList.size
    }

    inner class BasketViewHolder(private val binding: ItemBasketBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(basket: BasketItem) = binding.apply {
            initViews(basket)
        }

        private fun initViews(basket: BasketItem) = binding.apply {
            tvTitle.text = basket.title
            tvPrice.text = root.context.getString(
                R.string.dollars_with_point,
                basket.price.toString()
            )
            setImage(basket.pictureURL)
            initPlusAndMinus(basket)
            initBasketImage(basket)
            imageProduct.cornerRadius(20f)
            counterContainer.cornerRadius(35f)
        }

        private fun initBasketImage(basket: BasketItem) = binding.apply {
            imageBasket.setOnClickListener {
                basketList.indexOfFirst { it.id == basket.id }.takeIf { it != -1 }?.let { index ->
                    basketList.removeAt(index)
                    notifyItemRemoved(index)
                    changeTotalPriceCallBack.invoke(
                        tvCount.text.toString().toInt() * basket.price * -1
                    )
                }
            }
        }

        private fun initPlusAndMinus(basket: BasketItem) = binding.apply {
            imagePlus.setOnClickListener {
                val prevCount = tvCount.text.toString().toInt()
                tvCount.text = (prevCount + 1).toString()
                tvPrice.text = root.context.getString(
                    R.string.dollars_with_point,
                    (basket.price * tvCount.text.toString().toInt()).toString()
                )
                changeTotalPriceCallBack.invoke(basket.price)
            }
            imageMinus.setOnClickListener {
                val prevCount = tvCount.text.toString().toInt()
                if (prevCount > 1) {
                    tvCount.text = (prevCount - 1).toString()
                    tvPrice.text = root.context.getString(
                        R.string.dollars_with_point,
                        (tvCount.text.toString().toInt() * basket.price).toString()
                    )
                    changeTotalPriceCallBack.invoke(basket.price * -1)
                }
            }
        }

        private fun setImage(url: String) {
            Glide.with(binding.root.context)
                .load(url)
                .into(binding.imageProduct)
        }
    }
}