package com.example.effectivemobile.ui.main.adapter.delegate

import android.util.Log
import com.example.effectivemobile.databinding.ItemSearchBinding
import com.example.effectivemobile.ui.base.basedelegate.ViewBindingDelegateAdapter
import com.example.effectivemobile.ui.main.adapter.elements.SearchElement

class SearchAdapterDelegate : ViewBindingDelegateAdapter<SearchElement, ItemSearchBinding>(
    ItemSearchBinding::inflate
) {

    override fun ItemSearchBinding.onBind(item: SearchElement) {
    }

    override fun isForViewType(item: Any): Boolean = item is SearchElement

    override fun SearchElement.getItemId(): Any = id

    override fun ItemSearchBinding.onRecycled() {}

    override fun ItemSearchBinding.onAttachedToWindow() {
        Log.d(SearchAdapterDelegate::class.java.simpleName, "onAttachedToWindow")
    }

    override fun ItemSearchBinding.onDetachedFromWindow() {
        Log.d(SearchAdapterDelegate::class.java.simpleName, "onDetachedFromWindow")
    }
}