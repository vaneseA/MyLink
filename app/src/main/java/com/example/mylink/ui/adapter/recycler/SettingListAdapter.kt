package com.example.mylink.ui.adapter.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mylink.data.model.SettingItemValue
import com.example.mylink.databinding.ItemSettingBinding
import com.example.mylink.ui.adapter.basic.RecyclerBasicAdapter
import com.example.mylink.ui.adapter.basic.RecyclerBasicViewHolder

class SettingListAdapter :
    RecyclerBasicAdapter<SettingItemValue, SettingViewHolder>() {
    override fun onBindViewHolder(
        holder: SettingViewHolder,
        item: SettingItemValue
    ) {
        holder.setItem(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingViewHolder {
        val binding = ItemSettingBinding.inflate(LayoutInflater.from(parent.context))
        return SettingViewHolder(binding)
    }
}

class SettingViewHolder(binding: ItemSettingBinding) :
    RecyclerBasicViewHolder<ItemSettingBinding>(binding) {
    fun setItem(item: SettingItemValue){
        binding.item = item
        binding.root.setOnClickListener { item.open() }
    }
}

