package com.example.mylink.ui.adapter.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mylink.data.model.SjSearchWithTags
import com.example.mylink.data.model.SjTag
import com.example.mylink.databinding.ItemSearchSetBinding
import com.example.mylink.ui.adapter.basic.RecyclerBasicAdapter
import com.example.mylink.ui.adapter.basic.RecyclerBasicViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchSetAdapter(
    private val setSearchOperation: (String, List<SjTag>) -> Job,
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
        setSearchOperation: (String, List<SjTag>) -> Job,
        searchStartOperation: () -> Unit
    ) {
        binding.search = search
        itemView.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch{
                val setJob = setSearchOperation(search.search.keyword, search.tags)
                launch{
                    setJob.join()
                    searchStartOperation()
                }
            }
        }
        itemView.setOnLongClickListener {
            setSearchOperation(search.search.keyword, search.tags)
            true
        }
    }


}
