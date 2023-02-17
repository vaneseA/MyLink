package com.example.mylink.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mylink.data.model.SettingData
import com.example.mylink.databinding.ItemSettingBinding
import com.example.mylink.ui.adapter.basic.RecyclerBasicAdapter
import com.example.mylink.ui.adapter.basic.RecyclerBasicViewHolder

class RecyclerSettingAdapter :
    RecyclerBasicAdapter<SettingData, RecyclerSettingViewHolder>() {
    override fun onBindViewHolder(
        holder: RecyclerSettingViewHolder,
        item: SettingData
    ) {
        holder.setItem(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerSettingViewHolder {
        val binding = ItemSettingBinding.inflate(LayoutInflater.from(parent.context))
        return RecyclerSettingViewHolder(binding)
    }
}

class RecyclerSettingViewHolder(binding: ItemSettingBinding) :
    RecyclerBasicViewHolder<ItemSettingBinding>(binding) {
        fun setItem(item: SettingData){
            binding.item = item
            binding.root.setOnClickListener { item.open() }
        }
}

