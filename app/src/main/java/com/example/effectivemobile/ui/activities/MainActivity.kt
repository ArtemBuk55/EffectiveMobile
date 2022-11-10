package com.example.effectivemobile.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.ActivityMainBinding
import com.example.effectivemobile.ui.adapters.BestSellerAdapter
import com.example.effectivemobile.ui.adapters.CategoryAdapter
import com.example.effectivemobile.ui.adapters.HotSaleAdapter
import com.example.effectivemobile.ui.adapters.SpinnerAdapter
import com.example.effectivemobile.ui.items.*
import com.example.effectivemobile.ui.utils.cornerRadius
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val categoryAdapter = CategoryAdapter()
    private lateinit var hotSaleAdapter: HotSaleAdapter
    private lateinit var bestSellerAdapter: BestSellerAdapter
    private lateinit var filterOptionsContainer: BottomSheetBehavior<CardView>
    private val openDetailsActivityCallback = {
        openDetailsActivity()
    }
    private var selectedBrandPosition = 0
    private var selectedPricePosition = 0
    private var selectedSizePosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        initAdapters()
        initFilterOptions()
        initViews()
    }

    private fun openDetailsActivity() {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.ARG_DETAILS, getProductDetailsItem())
        startActivity(intent)
    }

    private fun openCartActivity() {
        val intent = Intent(this, CartActivity::class.java)
        intent.putExtra(CartActivity.ARG_CART, getCartResponseItem())
        startActivity(intent)
    }

    private fun initViews() = binding.apply {
        imageFilter.setOnClickListener {
            filterOptionsContainer.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun initAdapters() {
        initCategoryAdapter()
        initHotSaleAdapter()
        initBestSellerAdapter()
    }

    private fun initCategoryAdapter() = binding.apply {
        rvCategories.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoryAdapter
        categoryAdapter.addCategories(getCategoryList())
    }

    private fun initHotSaleAdapter() = binding.apply {
        hotSaleAdapter = HotSaleAdapter(openDetailsActivityCallback)
        rvHotSales.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        rvHotSales.adapter = hotSaleAdapter
        hotSaleAdapter.addHotSales(getHotSaleList())
    }

    private fun initBestSellerAdapter() = binding.apply {
        bestSellerAdapter = BestSellerAdapter(openDetailsActivityCallback)
        rvBestSeller.layoutManager = GridLayoutManager(applicationContext, 2)
        rvBestSeller.adapter = bestSellerAdapter
        bestSellerAdapter.addBestSeller(getBestSellerList())
    }

    private fun initFilterOptions() = binding.apply {
        initSpinners()
        initFilterOptionsContainer()
        btnDone.run {
            cornerRadius(25f)
            setOnClickListener {
                filterGood()
                filterOptionsContainer.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
        imageCancel.setOnClickListener {
            filterOptionsContainer.state = BottomSheetBehavior.STATE_COLLAPSED
            resetFilters()
        }
    }

    private fun initFilterOptionsContainer() = binding.apply {
        filterOptionsContainer = BottomSheetBehavior.from(optionsFilterContainer)
        filterOptionsContainer.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    resetFilters()
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })
    }

    private fun resetFilters() = binding.apply {
        spinBrand.setSelection(selectedBrandPosition)
        spinPrice.setSelection(selectedPricePosition)
        spinSize.setSelection(selectedSizePosition)
    }

    private fun initSpinners() {
        initSpinnerBrand()
        initSpinnerPrice()
        initSpinnerSize()
    }

    private fun filterGood(
    ) = binding.apply {
        if (selectedBrandPosition != spinBrand.selectedItemPosition
            || selectedPricePosition != spinPrice.selectedItemPosition
            || selectedSizePosition != spinSize.selectedItemPosition
        ) {
            //filter god
            selectedBrandPosition = spinBrand.selectedItemPosition
            selectedPricePosition = spinPrice.selectedItemPosition
            selectedBrandPosition = spinSize.selectedItemPosition
        }
    }

    private fun initSpinnerBrand() = binding.apply {
        spinBrand.run {
            val brandsList = getBrandList()
            adapter = SpinnerAdapter(context, brandsList)
            setSelection(0)
            selectedBrandPosition = 0
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //change brand
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }

    private fun initSpinnerPrice() = binding.apply {
        spinPrice.run {
            val priceList = getPriceList()
            adapter = SpinnerAdapter(context, priceList)
            setSelection(0)
            selectedPricePosition = 0
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //change price
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }

    private fun initSpinnerSize() = binding.apply {
        spinSize.run {
            val sizeList = getSizeList()
            adapter = SpinnerAdapter(context, sizeList)
            setSelection(0)
            selectedPricePosition = 0

            adapter = SpinnerAdapter(context, getSizeList())
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //change size
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }

    private fun getBrandList(): MutableList<String> {
        return mutableListOf("Samsung", "Apple", "Xiaomi")
    }

    private fun getPriceList(): MutableList<String> {
        return mutableListOf("$300-$500", "$500-$1000", "$1000-$1500")
    }

    private fun getSizeList(): MutableList<String> {
        return mutableListOf("4.5 to 5.5 inches", "5.5 to 5.9 inches", "5.9 to 6.7 inches")
    }

    private fun getCategoryList(): MutableList<CategoryItem> {
        return mutableListOf(
            CategoryItem("Phone", R.drawable.ic_phone, true),
            CategoryItem("Computer", R.drawable.ic_computer),
            CategoryItem("Health", R.drawable.ic_health_category),
            CategoryItem("Books", R.drawable.ic_books)
        )
    }

    private fun getHotSaleList(): MutableList<HotSaleItem> {
        return mutableListOf(
            HotSaleItem(
                1,
                true,
                "Iphone 12",
                "Súper. Mega. Rápido.",
                "https://img.ibxk.com.br/2020/09/23/23104013057475.jpg?w=1120&h=420&mode=crop&scale=both",
                true
            ),
            HotSaleItem(
                2,
                false,
                "Samsung Galaxy A71",
                "Súper. Mega. Rápido.",
                "https://cdn-2.tstatic.net/kupang/foto/bank/images/pre-order-samsung-galaxy-a71-laris-manis-inilah-rekomendasi-ponsel-harga-rp-6-jutaan.jpg",
                true
            ),
            HotSaleItem(
                3,
                false,
                "Xiaomi Mi 11 ultra",
                "Súper. Mega. Rápido.",
                "https://static.digit.in/default/942998b8b4d3554a6259aeb1a0124384dbe0d4d5.jpeg",
                true
            )
        )
    }

    private fun getBestSellerList(): MutableList<BestSellerItem> {
        return mutableListOf(
            BestSellerItem(
                111,
                true,
                "Samsung Galaxy s20 Ultra",
                1047,
                1500,
                "https://shop.gadgetufa.ru/images/upload/52534-smartfon-samsung-galaxy-s20-ultra-12-128-chernyj_1024.jpg"
            ),
            BestSellerItem(
                222,
                true,
                "Xiaomi Mi 10 Pro",
                300,
                400,
                "https://mi92.ru/wp-content/uploads/2020/03/smartfon-xiaomi-mi-10-pro-12-256gb-global-version-starry-blue-sinij-1.jpg"
            ),
            BestSellerItem(
                3333,
                true,
                "Samsung Note 20 Ultra",
                1047,
                1500,
                "https://opt-1739925.ssl.1c-bitrix-cdn.ru/upload/iblock/c01/c014d088c28d45b606ed8c58e5817172.jpg?160405904823488"
            ),
            BestSellerItem(
                4444,
                true,
                "Motorola One Edge ",
                300,
                400,
                "https://www.benchmark.rs/assets/img/news/edge1.jpg"
            )
        )
    }

    private fun getProductDetailsItem(): ProductDetailsItem {
        return ProductDetailsItem(
            "Exynos 990",
            "108 + 12 mp",
            mutableListOf(126, 252),
            mutableListOf("#772D03", "#010035"),
            3,
            mutableListOf(
                "https://avatars.mds.yandex.net/get-mpic/5235334/img_id5575010630545284324.png/orig",
                "https://www.manualspdf.ru/thumbs/products/l/1260237-samsung-galaxy-note-20-ultra.jpg"
            ),
            true,
            1500,
            4.5,
            "256 GB",
            "8 GB",
            "Galaxy Note 20 Ultra"
        )
    }

    private fun getCartResponseItem(): CartResponse {
        return CartResponse(
            mutableListOf(
                BasketItem(
                    1,
                    "https://www.manualspdf.ru/thumbs/products/l/1260237-samsung-galaxy-note-20-ultra.jpg",
                    1500,
                    "Galaxy Note 20 Ultra"
                ),
                BasketItem(
                    2,
                    "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/iphone-13-pro-silver-select?wid=470&hei=556&fmt=jpeg&qlt=95&.v=1631652954000",
                    1800,
                    "iPhone 13"
                )
            ),
            "Free",
            4,
            3300
        )
    }

    //test
}