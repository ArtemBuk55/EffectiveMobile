package com.example.effectivemobile.presentation.ui.main.adapter.inneradapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.effectivemobile.databinding.ItemHotSaleBinding
import com.example.effectivemobile.presentation.ui.main.models.HotSaleItem
import com.example.effectivemobile.presentation.utils.cornerRadius
import com.example.effectivemobile.presentation.utils.hideView
import com.example.effectivemobile.presentation.utils.showView

class HotSaleAdapter(private val openDetailsActivityCallback: () -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var hotSalesList: List<HotSaleItem> = mutableListOf()

    fun addHotSales(hotSalesList: List<HotSaleItem>) {
        this.hotSalesList = hotSalesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHotSaleBinding.inflate(layoutInflater, parent, false)
        return HotSaleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HotSaleViewHolder).onBind(hotSalesList[position])
    }

    override fun getItemCount(): Int {
        return hotSalesList.size
    }

    inner class HotSaleViewHolder(private val binding: ItemHotSaleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(hotSale: HotSaleItem) = binding.apply {
            initViews(hotSale)
        }

        private fun initViews(hotSale: HotSaleItem) = binding.apply {
            if (hotSale.isNew) {
                imageNewCircle.showView()
                tvNew.showView()
            }
            if (hotSale.isBuy) {
                btnBuy.run {
                    showView()
                    cornerRadius(10f)
                    setOnClickListener {
                        openDetailsActivityCallback.invoke()
                    }
                }
            } else {
                btnBuy.hideView()
            }
            tvTitle.text = hotSale.title
            tvSubtitle.text = hotSale.subtitle
            setImage(hotSale.pictureURL)
        }

        private fun setImage(url: String) {
            Glide.with(binding.root.context)
                .load(url)
                .into(binding.imageMain)
            binding.imageMain.cornerRadius(30f)
        }
    }
}