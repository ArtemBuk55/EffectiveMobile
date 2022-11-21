package com.example.effectivemobile.presentation.ui.details.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.ItemMemoryBinding
import com.example.effectivemobile.presentation.ui.details.models.ColorItem
import com.example.effectivemobile.presentation.ui.details.models.MemoryItem
import com.example.effectivemobile.presentation.utils.cornerRadius
import com.example.effectivemobile.presentation.utils.showView

class MemoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var memoryList: List<MemoryItem> = mutableListOf()

    fun addMemoryList(memoryList: List<MemoryItem>) {
        this.memoryList = memoryList
        notifyDataSetChanged()
    }

    private fun updateMemory(memory: MemoryItem) {
        memoryList.forEach { memory ->
            memory.isSelected = false
        }
        memory.isSelected = true
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMemoryBinding.inflate(layoutInflater, parent, false)
        return MemoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MemoryViewHolder).onBind(memoryList[position])
    }

    override fun getItemCount(): Int {
        return memoryList.size
    }

    inner class MemoryViewHolder(private val binding: ItemMemoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(memory: MemoryItem) = binding.apply {
            tvMemory.run {
                text = root.context.getString(
                    R.string.memory_gb,
                    memory.memory.toString()
                )
                setOnClickListener {
                    updateMemory(memory)
                    //change memory
                }
            }
            if (memory.isSelected) {
                memoryContainer.showView()
                tvMemory.setTextColor(Color.WHITE)
            } else {
                memoryContainer.visibility = View.INVISIBLE
                tvMemory.setTextColor(root.resources.getColor(R.color.memory_not_selected))
            }
            memoryContainer.cornerRadius(25f)
        }
    }
}