package com.example.effectivemobile.presentation.ui.main.adapter.delegate

import android.util.Log
import com.example.effectivemobile.databinding.ItemHotSalesBinding
import com.example.effectivemobile.presentation.ui.main.adapter.inneradapters.HotSaleAdapter
import com.example.effectivemobile.presentation.ui.base.basedelegate.ViewBindingDelegateAdapter
import com.example.effectivemobile.presentation.ui.main.adapter.elements.HotSalesElement

class HotSalesDelegate(private val openDetailsActivityCallback: () -> Unit) : ViewBindingDelegateAdapter<HotSalesElement, ItemHotSalesBinding>(
    ItemHotSalesBinding::inflate
) {

    override fun ItemHotSalesBinding.onBind(item: HotSalesElement) {
        if (viewPager.adapter == null) {
            viewPager.adapter = HotSaleAdapter(openDetailsActivityCallback)
        }
        (viewPager.adapter as? HotSaleAdapter)?.addHotSales(item.hotItems)
    }

    override fun isForViewType(item: Any): Boolean = item is HotSalesElement

    override fun HotSalesElement.getItemId(): Any = id

    override fun ItemHotSalesBinding.onRecycled() {}

    override fun ItemHotSalesBinding.onAttachedToWindow() {
        Log.d(SearchAdapterDelegate::class.java.simpleName, "onAttachedToWindow")
    }

    override fun ItemHotSalesBinding.onDetachedFromWindow() {
        Log.d(SearchAdapterDelegate::class.java.simpleName, "onDetachedFromWindow")
    }
}