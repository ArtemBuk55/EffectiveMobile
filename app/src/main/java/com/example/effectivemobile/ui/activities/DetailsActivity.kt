package com.example.effectivemobile.ui.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.ActivityDetailsBinding
import com.example.effectivemobile.ui.adapters.ColorAdapter
import com.example.effectivemobile.ui.adapters.MemoryAdapter
import com.example.effectivemobile.ui.adapters.ProductPhotoAdapter
import com.example.effectivemobile.ui.items.*
import com.example.effectivemobile.ui.utils.cornerRadius
import com.example.effectivemobile.ui.utils.showView

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var productDetails: ProductDetailsItem
    private val productPhotoAdapter = ProductPhotoAdapter()
    private val colorAdapter = ColorAdapter()
    private val memoryAdapter = MemoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater).also { setContentView(it.root) }
        productDetails = intent.getParcelableExtra(ARG_DETAILS)!!
        initViews()
        initProductPhotoAdapter()
    }

    private fun initViews() = binding.apply {
        tvTitle.text = productDetails.title
        tvCPU.text = productDetails.CPU
        tvCamera.text = productDetails.camera
        tvRam.text = productDetails.ssd
        tvMemory.text = productDetails.sd
        tvPrice.text = root.context.getString(
            R.string.dollars_with_point,
            productDetails.price.toString()
        )
        btnAddToCart.cornerRadius(20f)
        detailsContainer.cornerRadius(70f, false)
        imageBack.setOnClickListener { finish() }
        imageCart.setOnClickListener {
            openCartActivity()
        }
        initIsFavourite()
        initCategories()
        addStars()
        addColors()
        addMemory()
    }

    private fun openCartActivity() {
        val intent = Intent(this, CartActivity::class.java)
        intent.putExtra(CartActivity.ARG_CART, getCartResponseItem())
        startActivity(intent)
    }

    private fun initProductPhotoAdapter() = binding.apply {
        productPhotoAdapter.addPhotoUrls(productDetails.images)
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

    companion object {
        const val ARG_DETAILS = "ARG_DETAILS"
    }
}