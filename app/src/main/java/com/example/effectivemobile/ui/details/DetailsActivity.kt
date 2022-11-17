package com.example.effectivemobile.ui.details

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.ActivityDetailsBinding
import com.example.effectivemobile.network.model.GetDetailsRequest
import com.example.effectivemobile.ui.cart.CartActivity
import com.example.effectivemobile.ui.details.adapters.ColorAdapter
import com.example.effectivemobile.ui.details.adapters.MemoryAdapter
import com.example.effectivemobile.ui.details.adapters.ProductPhotoAdapter
import com.example.effectivemobile.ui.details.models.ColorItem
import com.example.effectivemobile.ui.details.models.MemoryItem
import com.example.effectivemobile.ui.details.viewmodel.DetailsViewModel
import com.example.effectivemobile.utils.cornerRadius
import com.example.effectivemobile.utils.showView

class DetailsActivity : AppCompatActivity() {

    private val TAG = "DetailsActivity"

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var productDetails: GetDetailsRequest
    private val productPhotoAdapter = ProductPhotoAdapter()
    private val colorAdapter = ColorAdapter()
    private val memoryAdapter = MemoryAdapter()
    private lateinit var detailsViewModel: DetailsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater).also { setContentView(it.root) }
        initViews()
        detailsViewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
        startObservers()
        detailsViewModel.launchSearch()
        initProductPhotoAdapter()
    }

    private fun initViews() = binding.apply {
        btnAddToCart.cornerRadius(20f)
        detailsContainer.cornerRadius(70f, false)
        imageBack.setOnClickListener { finish() }
        imageCart.setOnClickListener {
            openCartActivity()
        }
        imageFavorite.setOnClickListener {
            if (productDetails.isFavorites) {
                imageHeart.setImageResource(R.drawable.ic_empty_white_heart)
                productDetails.isFavorites = false
                //delete from favorite
            } else {
                imageHeart.setImageResource(R.drawable.ic_white_heart)
                productDetails.isFavorites = true
                //add to favorite
            }
        }
        initCategories()
    }

    private fun startObservers() {
        detailsViewModel.details.observe(this) {
            productDetails = it
            setViewsInfo()
            productPhotoAdapter.addPhotoUrls(productDetails.images)
        }
    }

    private fun setViewsInfo() = binding.apply {
        tvTitle.text = productDetails.title
        tvCPU.text = productDetails.CPU
        tvCamera.text = productDetails.camera
        tvRam.text = productDetails.ssd
        tvMemory.text = productDetails.sd
        tvPrice.text = root.context.getString(
            R.string.dollars_with_point,
            productDetails.price.toString()
        )
        initIsFavourite()
        addStars()
        addColors()
        addMemory()
    }

    private fun openCartActivity() {
        val intent = Intent(this, CartActivity::class.java)
        startActivity(intent)
    }

    private fun initProductPhotoAdapter() = binding.apply {
        rvProductPhotos.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        rvProductPhotos.adapter = productPhotoAdapter
    }

    private fun addStars() = binding.apply {
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.marginEnd = 25
        for (index in 0 until productDetails.rating.toInt()) {
            val imageView = ImageView(this@DetailsActivity)
            imageView.setImageResource(R.drawable.ic_star)
            imageView.layoutParams = layoutParams
            starsContainer.addView(imageView)
        }
        if ((productDetails.rating*10).toInt()%10!=0){
            val imageView = ImageView(this@DetailsActivity)
            imageView.setImageResource(R.drawable.ic_half_star)
            imageView.layoutParams = layoutParams
            starsContainer.addView(imageView)
        }
    }

    private fun addColors() = binding.apply {
        rvColors.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        rvColors.adapter = colorAdapter
        val colorList = mutableListOf<ColorItem>()
        for (color in productDetails.color) {
            colorList.add(ColorItem(color))
        }
        colorList[0].isSelected = true
        colorAdapter.addColorsList(colorList)
    }

    private fun addMemory() = binding.apply {
        rvMemory.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        rvMemory.adapter = memoryAdapter
        val memoryList = mutableListOf<MemoryItem>()
        for (memory in productDetails.capacity) {
            memoryList.add(MemoryItem(memory))
        }
        memoryList[0].isSelected = true
        memoryAdapter.addMemoryList(memoryList)
    }

    private fun initIsFavourite() = binding.apply {
        if (productDetails.isFavorites) {
            imageHeart.setImageResource(R.drawable.ic_white_heart)
        }
    }

    private fun initCategories() = binding.apply {
        val bold = Typeface.create(
            ResourcesCompat.getFont(this@DetailsActivity, R.font.mark_pro),
            Typeface.BOLD
        )
        val normal = Typeface.create(
            ResourcesCompat.getFont(this@DetailsActivity, R.font.mark_pro),
            Typeface.NORMAL
        )
        tvShop.run {
            setOnClickListener {
                typeface = bold
                lineShop.showView()
                tvDetails.typeface = normal
                lineDetails.visibility = View.INVISIBLE
                tvFeatures.typeface = normal
                lineFeatures.visibility = View.INVISIBLE
            }
        }
        tvDetails.run {
            setOnClickListener {
                typeface = bold
                lineDetails.showView()
                tvShop.typeface = normal
                lineShop.visibility = View.INVISIBLE
                tvFeatures.typeface = normal
                lineFeatures.visibility = View.INVISIBLE
            }
        }
        tvFeatures.run {
            setOnClickListener {
                typeface = bold
                lineFeatures.showView()
                tvShop.typeface = normal
                lineShop.visibility = View.INVISIBLE
                tvDetails.typeface = normal
                lineDetails.visibility = View.INVISIBLE
            }
        }
    }

//    private fun getProductDetailsItem(): ProductDetailsItem {
//        return ProductDetailsItem(
//            "Exynos 990",
//            "108 + 12 mp",
//            mutableListOf(126, 252),
//            mutableListOf("#772D03", "#010035"),
//            3,
//            mutableListOf(
//                "https://avatars.mds.yandex.net/get-mpic/5235334/img_id5575010630545284324.png/orig",
//                "https://www.manualspdf.ru/thumbs/products/l/1260237-samsung-galaxy-note-20-ultra.jpg"
//            ),
//            true,
//            1500,
//            4.5,
//            "256 GB",
//            "8 GB",
//            "Galaxy Note 20 Ultra"
//        )
//    }

    companion object {
        const val ARG_DETAILS = "ARG_DETAILS"
    }
}