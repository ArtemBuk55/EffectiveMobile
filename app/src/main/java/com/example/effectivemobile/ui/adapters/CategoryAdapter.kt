package com.example.effectivemobile.ui.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.ItemCategoryBinding
import com.example.effectivemobile.ui.items.CategoryItem

class CategoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var categoriesList: MutableList<CategoryItem> = mutableListOf()

    fun addCategories(categoriesList: MutableList<CategoryItem>) {
        this.categoriesList = categoriesList
        notifyDataSetChanged()
    }

    private fun updateCategories(category: CategoryItem){
        categoriesList.forEach { categoryItem ->
            categoryItem.isSelected = false
        }
        category.isSelected = true
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CategoryViewHolder).onBind(categoriesList[position])
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(category: CategoryItem) = binding.apply {
            initViews(category)
            initClickListener(category)
        }

        private fun initViews(category: CategoryItem) = binding.apply {
            imageIcon.setImageResource(category.iconResId)
            tvTitle.text = category.title
            if (category.isSelected) {
                selectCategory()
            } else {
                unselectCategory()
            }
        }

        private fun selectCategory() = binding.apply {
            imageCircle.setColorFilter(root.context.resources.getColor(R.color.selected_item_color))
            imageIcon.setColorFilter(Color.WHITE)
            tvTitle.setTextColor(root.context.resources.getColor(R.color.selected_item_color))
        }

        private fun unselectCategory() = binding.apply {
            imageCircle.setColorFilter(Color.WHITE)
            imageIcon.setColorFilter(root.context.resources.getColor(R.color.unselected_item_color))
            tvTitle.setTextColor(root.context.resources.getColor(R.color.titles_text_color))
        }

        private fun initClickListener(category: CategoryItem) = binding.apply {
            root.setOnClickListener {
                updateCategories(category)
                Log.d("MyTag", "change category")
            }
        }
    }
}