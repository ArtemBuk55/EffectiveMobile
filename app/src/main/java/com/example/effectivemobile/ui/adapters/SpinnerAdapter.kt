package com.example.effectivemobile.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.effectivemobile.databinding.ItemSpinnerBinding

class SpinnerAdapter(context: Context, private val itemList: List<String>) :
    ArrayAdapter<String>(context, 0, itemList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, parent)
    }

    private fun getCustomView(position: Int, parent: ViewGroup): View {
        val binding = ItemSpinnerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.title.text = itemList[position]
        return binding.root
    }
}