package com.example.effectivemobile.ui.details.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.effectivemobile.databinding.ItemProductPhotoBinding

class ProductPhotoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var photoUrlsList: MutableList<String> = mutableListOf()

    fun addPhotoUrls(photoUrlsList: List<String>) {
        this.photoUrlsList.clear()
        this.photoUrlsList.addAll(photoUrlsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductPhotoBinding.inflate(layoutInflater, parent, false)
        return ProductPhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductPhotoViewHolder).onBind(photoUrlsList[position])
    }

    override fun getItemCount(): Int {
        return photoUrlsList.size
    }

    inner class ProductPhotoViewHolder(private val binding: ItemProductPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(photoUrl: String) = binding.apply {
            Glide.with(binding.root.context)
                .load(photoUrl)
                .into(imagePhoto)
        }
    }
}