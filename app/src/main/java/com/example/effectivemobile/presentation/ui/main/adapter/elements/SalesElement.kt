package com.example.effectivemobile.presentation.ui.main.adapter.elements

import com.example.effectivemobile.presentation.ui.base.basedelegate.AdapterElement
import com.example.effectivemobile.presentation.ui.main.models.BestSellerItem
import com.example.effectivemobile.presentation.ui.main.models.CategoryItem
import com.example.effectivemobile.presentation.ui.main.models.HotSaleItem

data class CategoriesElement(val categoryList: List<CategoryItem>) :
    AdapterElement("sales_categories_block")

data class SearchElement(val text: String) : AdapterElement("search_block")

data class HotSalesElement(val hotItems: List<HotSaleItem>) : AdapterElement("sales_hot_block")

data class BestSellerElement(val bestSeller: List<BestSellerItem>) :
    AdapterElement("sales_best_block")