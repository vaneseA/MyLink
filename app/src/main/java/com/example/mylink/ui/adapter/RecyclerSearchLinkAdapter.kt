package com.example.mylink.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mylink.data.model.SjLinksAndDomainsWithTags
import com.example.mylink.databinding.ItemLinkSearchBinding
import com.example.mylink.ui.adapter.basic.RecyclerBasicAdapter
import com.example.mylink.ui.adapter.basic.RecyclerBasicViewHolder

class RecyclerSearchLinkAdapter(
    private val detailOperation: (Int) -> Unit,
) :
    RecyclerBasicAdapter<SjLinksAndDomainsWithTags, LinksSearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinksSearchViewHolder {
        val binding = ItemLinkSearchBinding.inflate(LayoutInflater.from(parent.context))
        return LinksSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LinksSearchViewHolder, item: SjLinksAndDomainsWithTags) {
        holder.setLink(item, detailOperation)
    }
}

class LinksSearchViewHolder(binding: ItemLinkSearchBinding) :
    RecyclerBasicViewHolder<ItemLinkSearchBinding>(binding) {

    fun setLink(
        link: SjLinksAndDomainsWithTags,
        detailOperation: (Int) -> Unit
    ) {
        binding.data = link
        binding.root.setOnClickListener { detailOperation(link.link.lid) }
    }

}