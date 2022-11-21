package com.example.effectivemobile.presentation.ui.main

import com.example.effectivemobile.R
import com.example.effectivemobile.presentation.ui.base.basedelegate.AdapterElement
import com.example.effectivemobile.domain.sales.BestSeller
import com.example.effectivemobile.domain.sales.HotSales
import com.example.effectivemobile.domain.sales.Sales
import com.example.effectivemobile.presentation.ui.main.adapter.elements.BestSellerElement
import com.example.effectivemobile.presentation.ui.main.adapter.elements.CategoriesElement
import com.example.effectivemobile.presentation.ui.main.adapter.elements.HotSalesElement
import com.example.effectivemobile.presentation.ui.main.adapter.elements.SearchElement
import com.example.effectivemobile.presentation.ui.main.models.BestSellerItem
import com.example.effectivemobile.presentation.ui.main.models.CategoryItem
import com.example.effectivemobile.presentation.ui.main.models.HotSaleItem


object SalesTransformator {

    private val TAG = "SalesTransformator"

    fun transform(
        salesRequest: Sales
    ) = emptySequence<AdapterElement>()
        .plus(getCategories())
        .plus(getSearch())
        .plus(getHotSales(salesRequest.hotSales))
        .plus(getBestSaler(salesRequest.bestSeller))
        .toList()

    private fun getCategories(): CategoriesElement { // Since we dont have info from api about categories, I use hardcode
        return CategoriesElement(
            listOf<CategoryItem>(
                CategoryItem("Phone", R.drawable.ic_phone, true),
                CategoryItem("Computer", R.drawable.ic_computer),
                CategoryItem("Health", R.drawable.ic_health_category),
                CategoryItem("Books", R.drawable.ic_books)
            )
        )
    }

    private fun getSearch(): SearchElement {
        return SearchElement(
            ""
        )
    }

    private fun getBestSaler(bestSeller: List<BestSeller>): BestSellerElement {
        return BestSellerElement(
            bestSeller.toBestSellerItems()
        )
    }

    private fun getHotSales(homeSeller: List<HotSales>): HotSalesElement {
        return HotSalesElement(
            homeSeller.toHotSaleItem()
        )
    }
}

private fun List<BestSeller>.toBestSellerItems(): List<BestSellerItem> {
    return this.map {
        BestSellerItem(
            id = it.id,
            isFavorites = it.isFavorites,
            title = it.title,
            priceWithoutDiscount = it.priceWithoutDiscount,
            discountPrice = it.discountPrice,
            pictureURL = it.picture
        )
    }
}

private fun List<HotSales>.toHotSaleItem(): List<HotSaleItem> {
    return this.map {
        HotSaleItem(
            id = it.id,
            isNew = it.isNew,
            isBuy = it.isBuy,
            title = it.title,
            subtitle = it.subtitle,
            pictureURL = it.picture
        )
    }
}
