package com.example.effectivemobile.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobile.databinding.ItemColorBinding
import com.example.effectivemobile.ui.items.ColorItem
import com.example.effectivemobile.ui.utils.hideView
import com.example.effectivemobile.ui.utils.showView

class ColorAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var colorsList: MutableList<ColorItem> = mutableListOf()

    fun addColorsList(colorsList: MutableList<ColorItem>) {
        this.colorsList = colorsList
        notifyDataSetChanged()
    }

    private fun updateColors(color: ColorItem) {
        colorsList.forEach { color ->
            color.isSelected = false
        }
        color.isSelected = true
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemColorBinding.inflate(layoutInflater, parent, false)
        return ColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ColorViewHolder).onBind(colorsList[position])
    }

    override fun getItemCount(): Int {
        return colorsList.size
    }

    inner class ColorViewHolder(private val binding: ItemColorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(color: ColorItem) = binding.apply {
            imageCircle.setColorFilter(Color.parseColor(color.color))
            if (color.isSelected) {
                imageCheckMark.showView()
            } else {
                imageCheckMark.hideView()
            }
            imageCircle.setOnClickListener {
                updateColors(color)
                //change color
            }
        }
    }
}