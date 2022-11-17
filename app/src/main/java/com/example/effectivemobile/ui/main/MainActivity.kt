package com.example.effectivemobile.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobile.databinding.ActivityMainBinding
import com.example.effectivemobile.ui.details.DetailsActivity
import com.example.effectivemobile.ui.base.basedelegate.CompositeDelegateAdapter
import com.example.effectivemobile.ui.main.adapter.delegate.BestSellerDelegate
import com.example.effectivemobile.ui.main.adapter.delegate.CategoriesAdapterDelegate
import com.example.effectivemobile.ui.main.adapter.delegate.HotSalesDelegate
import com.example.effectivemobile.ui.main.adapter.delegate.SearchAdapterDelegate
import com.example.effectivemobile.ui.main.adapter.inneradapters.SpinnerAdapter
import com.example.effectivemobile.ui.main.viewmodel.MainViewModel
import com.example.effectivemobile.utils.cornerRadius
import com.google.android.material.bottomsheet.BottomSheetBehavior


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    private lateinit var filterOptionsContainer: BottomSheetBehavior<CardView>

    private lateinit var mainViewModel: MainViewModel

    private val openDetailsActivityCallback = {
        openDetailsActivity()
    }

    private val adapter = CompositeDelegateAdapter(
        BestSellerDelegate(openDetailsActivityCallback),
        CategoriesAdapterDelegate(),
        HotSalesDelegate(openDetailsActivityCallback),
        SearchAdapterDelegate()
    )

    private var selectedBrandPosition = 0
    private var selectedPricePosition = 0
    private var selectedSizePosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.recyclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        startObservers()

        initFilterOptions()
        initViews()
        mainViewModel.launchSearch()
    }

    private fun startObservers() {
        mainViewModel.sales.observe(this) {
            adapter.swapData(SalesTransformator.transform(it))
        }
    }

    private fun openDetailsActivity() {
        val intent = Intent(this, DetailsActivity::class.java)
        startActivity(intent)
    }

    private fun initViews() = binding.apply {
        imageFilter.setOnClickListener {
            filterOptionsContainer.state = BottomSheetBehavior.STATE_EXPANDED
        }
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
}