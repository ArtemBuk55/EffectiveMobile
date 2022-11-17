package com.example.effectivemobile.ui.main.adapter.delegate

import android.util.Log
import com.example.effectivemobile.databinding.ItemCategoriesBinding
import com.example.effectivemobile.ui.main.adapter.inneradapters.CategoryAdapter
import com.example.effectivemobile.ui.base.basedelegate.ViewBindingDelegateAdapter
import com.example.effectivemobile.ui.main.adapter.elements.CategoriesElement

class CategoriesAdapterDelegate : ViewBindingDelegateAdapter<CategoriesElement, ItemCategoriesBinding>(
    ItemCategoriesBinding::inflate
) {

    private val adapter = CategoryAdapter()

    override fun ItemCategoriesBinding.onBind(item: CategoriesElement) {
        if (recyclerView.adapter == null) {
            recyclerView.adapter = adapter
        }
        adapter.setData(item.categoryList)
    }

    override fun isForViewType(item: Any): Boolean = item is CategoriesElement

    override fun CategoriesElement.getItemId(): Any = id

    override fun ItemCategoriesBinding.onRecycled() {}

    override fun ItemCategoriesBinding.onAttachedToWindow() {
        Log.d(CategoriesAdapterDelegate::class.java.simpleName, "onAttachedToWindow")
    }

    override fun ItemCategoriesBinding.onDetachedFromWindow() {
        Log.d(CategoriesAdapterDelegate::class.java.simpleName, "onDetachedFromWindow")
    }
}