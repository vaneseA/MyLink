package com.example.mylink.ui.adapter.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mylink.data.model.SjSearchWithTags
import com.example.mylink.data.model.SjTag
import com.example.mylink.databinding.ItemSearchSetBinding
import com.example.mylink.ui.adapter.basic.RecyclerBasicAdapter
import com.example.mylink.ui.adapter.basic.RecyclerBasicViewHolder

class SearchSetAdapter(
    private val setSearchOperation: (String, List<SjTag>) -> Unit,
    private val searchStartOperation: () -> Unit
) : RecyclerBasicAdapter<SjSearchWithTags, SearchSetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchSetViewHolder {
        val binding =
            ItemSearchSetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchSetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchSetViewHolder, item: SjSearchWithTags) {
        holder.setSearch(item, setSearchOperation, searchStartOperation)
    }


}

class SearchSetViewHolder(binding: ItemSearchSetBinding) :
    RecyclerBasicViewHolder<ItemSearchSetBinding>(binding) {

    fun setSearch(
        search: SjSearchWithTags,
        setSearchOperation: (String, List<SjTag>) -> Unit,
        searchStartOperation: () -> Unit
    ) {
        binding.search = search
        itemView.setOnClickListener {
            setSearchOperation(search.search.keyword, search.tags)
            searchStartOperation()
        }
        itemView.setOnLongClickListener {
            setSearchOperation(search.search.keyword, search.tags)
            true
        }
    }


}
