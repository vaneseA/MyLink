package com.example.mylink.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mylink.data.model.SjLink
import com.example.mylink.data.model.SjLinksAndDomainsWithTags
import com.example.mylink.data.model.SjTag
import com.example.mylink.databinding.ItemLinksBinding
import com.example.mylink.ui.adapter.basic.SjDataBindingViewHolder
import com.example.mylink.ui.adapter.basic.SjRecyclerAdapter

class RecyclerLinkAdapter(
    private val openOperation: (String) -> Unit,
    private val updateOperation: (Int) -> Unit,
    private val deleteOperation: (SjLink, List<SjTag>) -> Unit
) :
    SjRecyclerAdapter<SjLinksAndDomainsWithTags, LinksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinksViewHolder {
        val binding = ItemLinksBinding.inflate(LayoutInflater.from(parent.context))
        return LinksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LinksViewHolder, item: SjLinksAndDomainsWithTags) {
        holder.setLink(item,openOperation,updateOperation,deleteOperation)
    }
}

class LinksViewHolder(binding: ItemLinksBinding) :
    SjDataBindingViewHolder<ItemLinksBinding>(binding) {

    fun setLink(
        link: SjLinksAndDomainsWithTags,
        openOperation: (String) -> Unit,
        updateOperation: (Int) -> Unit,
        deleteOperation: (SjLink, List<SjTag>) -> Unit
    ) {
        binding.link = link
        binding.linksItemWebButton.setOnClickListener { openOperation("${link.domain.url}${link.link.url}") }
        binding.linksItemEditButton.setOnClickListener { updateOperation(link.link.lid) }
        binding.linksItemDeleteButton.setOnClickListener { deleteOperation(link.link, link.tags) }
    }

}