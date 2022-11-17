package com.example.effectivemobile.ui.main

import com.example.effectivemobile.R
import com.example.effectivemobile.ui.base.basedelegate.AdapterElement
import com.example.effectivemobile.ui.main.adapter.elements.BestSellerElement
import com.example.effectivemobile.ui.main.adapter.elements.CategoriesElement
import com.example.effectivemobile.ui.main.adapter.elements.HotSalesElement
import com.example.effectivemobile.ui.main.adapter.elements.SearchElement
import com.example.effectivemobile.ui.main.models.BestSellerItem
import com.example.effectivemobile.ui.main.models.CategoryItem
import com.example.effectivemobile.ui.main.models.HotSaleItem
import com.example.effectivemobile.network.model.BestSellerData
import com.example.effectivemobile.network.model.GetSalesRequest
import com.example.effectivemobile.network.model.HomeStoreData


object SalesTransformator {

    private val TAG = "SalesTransformator"

    fun transform(
        salesRequest: GetSalesRequest
    ) = emptySequence<AdapterElement>()
        .plus(getCategories())
        .plus(getSearch())
        .plus(getHotSales(salesRequest.homeStoreData))
        .plus(getBestSaler(salesRequest.bestSellerData))
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

    fun getSearch(): SearchElement {
        return SearchElement(
            ""
        )
    }

    fun getBestSaler(bestSellerData: List<BestSellerData>): BestSellerElement {
        return BestSellerElement(
            bestSellerData.toBestSellerItems()
        )
    }

    fun getHotSales(homeSeller: List<HomeStoreData>): HotSalesElement {
        return HotSalesElement(
            homeSeller.toHotSaleItem()
        )
    }
}

private fun List<BestSellerData>.toBestSellerItems(): List<BestSellerItem> {
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

private fun List<HomeStoreData>.toHotSaleItem(): List<HotSaleItem> {
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
