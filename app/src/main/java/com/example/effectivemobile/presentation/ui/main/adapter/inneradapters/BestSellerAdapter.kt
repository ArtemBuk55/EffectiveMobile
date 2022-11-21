package com.example.effectivemobile.presentation.ui.main.adapter.inneradapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.ItemBestSellerBinding
import com.example.effectivemobile.presentation.ui.main.models.BestSellerItem
import com.example.effectivemobile.presentation.utils.cornerRadius

class BestSellerAdapter(private val openDetailsActivityCallback: () -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var bestSellerList: List<BestSellerItem> = mutableListOf()

    fun setData(bestSellerList: List<BestSellerItem>) {
        this.bestSellerList = bestSellerList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBestSellerBinding.inflate(layoutInflater, parent, false)
        return BestSellerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BestSellerViewHolder).onBind(bestSellerList[position])
    }

    override fun getItemCount(): Int {
        return bestSellerList.size
    }

    inner class BestSellerViewHolder(private val binding: ItemBestSellerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(bestSeller: BestSellerItem) = binding.apply {
            initViews(bestSeller)
            initClickListener()
        }

        private fun initViews(bestSeller: BestSellerItem) = binding.apply {
            root.cornerRadius(30f)
            tvTitle.text = bestSeller.title
            tvPrice.text = root.context.getString(
                R.string.dollars,
                bestSeller.priceWithoutDiscount.toString()
            )
            tvPriceWithoutDiscount.text = root.context.getString(
                R.string.dollars,
                bestSeller.discountPrice.toString()
            )
            //цены были поменяны так как с сервера приходят данные с опечаткой
            tvPriceWithoutDiscount.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            setImage(bestSeller.pictureURL)
            if (bestSeller.isFavorites){
                imageHeart.setImageResource(R.drawable.ic_heart)
            }
            imageFavorite.setOnClickListener {
                if (bestSeller.isFavorites) {
                    imageHeart.setImageResource(R.drawable.ic_empty_orange_heart)
                    bestSeller.isFavorites = false
                    //delete from favorite
                } else{
                    imageHeart.setImageResource(R.drawable.ic_heart)
                    bestSeller.isFavorites = true
                    //add to favorite
                }
            }
        }

        private fun initClickListener() = binding.apply {
            root.setOnClickListener {
                openDetailsActivityCallback.invoke()
            }
        }

        private fun setImage(url: String) {
            Glide.with(binding.root.context)
                .load(url)
                .into(binding.imageGood)
        }
    }
}