package com.example.effectivemobile.ui.main.adapter.delegate

import android.util.Log
import com.example.effectivemobile.databinding.ItemsBestSellerBinding
import com.example.effectivemobile.ui.main.adapter.inneradapters.BestSellerAdapter
import com.example.effectivemobile.ui.base.basedelegate.ViewBindingDelegateAdapter
import com.example.effectivemobile.ui.main.adapter.elements.BestSellerElement

class BestSellerDelegate(private val openDetailsActivityCallback: () -> Unit) : ViewBindingDelegateAdapter<BestSellerElement, ItemsBestSellerBinding>(
    ItemsBestSellerBinding::inflate
) {

    override fun ItemsBestSellerBinding.onBind(item: BestSellerElement) {
        if (recyclerView.adapter == null) {
            recyclerView.adapter = BestSellerAdapter(openDetailsActivityCallback)
        }
        (recyclerView.adapter as? BestSellerAdapter)?.setData(item.bestSeller)
    }

    override fun isForViewType(item: Any): Boolean = item is BestSellerElement

    override fun BestSellerElement.getItemId(): Any = id

    override fun ItemsBestSellerBinding.onRecycled() {}

    override fun ItemsBestSellerBinding.onAttachedToWindow() {
        Log.d(SearchAdapterDelegate::class.java.simpleName, "onAttachedToWindow")
    }

    override fun ItemsBestSellerBinding.onDetachedFromWindow() {
        Log.d(SearchAdapterDelegate::class.java.simpleName, "onDetachedFromWindow")
    }
}