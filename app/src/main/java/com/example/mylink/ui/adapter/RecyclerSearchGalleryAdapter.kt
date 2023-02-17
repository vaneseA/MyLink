package com.example.mylink.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mylink.data.model.SjSearchWithTags
import com.example.mylink.data.model.SjTag
import com.example.mylink.databinding.ItemGallerySearchSetBinding
import com.example.mylink.ui.adapter.basic.RecyclerBasicAdapter
import com.example.mylink.ui.adapter.basic.RecyclerBasicViewHolder

class RecyclerSearchGalleryAdapter(
    private val clickOperation: (String, List<SjTag>) -> Unit
) : RecyclerBasicAdapter<SjSearchWithTags, SearchesGalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchesGalleryViewHolder {
        val binding =
            ItemGallerySearchSetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchesGalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchesGalleryViewHolder, item: SjSearchWithTags) {
        holder.setSearch(item, clickOperation)
    }

}

class SearchesGalleryViewHolder(binding: ItemGallerySearchSetBinding) :
    RecyclerBasicViewHolder<ItemGallerySearchSetBinding>(
        binding
    ) {

    @SuppressLint("ClickableViewAccessibility")
    fun setSearch(
        search: SjSearchWithTags,
        clickOperation: (String, List<SjTag>) -> Unit
    ) {

        binding.search = search
        itemView.setOnClickListener {
            clickOperation(search.search.keyword, search.tags)
        }
        val searchWord = search.search.keyword
        if (searchWord.isEmpty()) {
            binding.keywordTextView.visibility = View.GONE
        } else {
            binding.keywordTextView.visibility = View.VISIBLE
            binding.keywordTextView.setText(search.search.keyword)
        }
    }

}
