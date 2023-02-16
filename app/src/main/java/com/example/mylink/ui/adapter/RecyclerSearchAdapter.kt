package com.example.mylink.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mylink.data.model.SjSearchWithTags
import com.example.mylink.data.model.SjTag
import com.example.mylink.databinding.ItemSearchSetBinding
import com.example.mylink.ui.adapter.basic.RecyclerBasicAdapter
import com.example.mylink.ui.adapter.basic.RecyclerBasicViewHolder

class RecyclerSearchAdapter(
    private val clickOperation: (String, List<SjTag>) -> Unit
) : RecyclerBasicAdapter<SjSearchWithTags, SearchesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchesViewHolder {
        val binding =
            ItemSearchSetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchesViewHolder, item: SjSearchWithTags) {
        holder.setSearch(item, clickOperation)
    }

}

class SearchesViewHolder(binding: ItemSearchSetBinding) :
    RecyclerBasicViewHolder<ItemSearchSetBinding>(
        binding
    ) {

    fun setSearch(
        search: SjSearchWithTags,
        clickOperation: (String, List<SjTag>) -> Unit
    ) {
        binding.search = search
        itemView.setOnClickListener {
            clickOperation(search.search.keyword, search.tags)
        }
    }

}