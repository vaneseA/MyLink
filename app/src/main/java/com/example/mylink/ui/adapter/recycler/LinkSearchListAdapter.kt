package com.example.mylink.ui.adapter.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mylink.R
import com.example.mylink.data.model.ELinkType
import com.example.mylink.data.model.SjLinksAndDomainsWithTags
import com.example.mylink.databinding.ItemLinkSearchBinding
import com.example.mylink.ui.adapter.basic.RecyclerBasicAdapter
import com.example.mylink.ui.adapter.basic.RecyclerBasicViewHolder


class LinkSearchListAdapter(
    private val detailOperation: (Int) -> Unit,
) :
    RecyclerBasicAdapter<SjLinksAndDomainsWithTags, LinkSearchListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkSearchListViewHolder {
        val binding = ItemLinkSearchBinding.inflate(LayoutInflater.from(parent.context))
        return LinkSearchListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LinkSearchListViewHolder, item: SjLinksAndDomainsWithTags) {
        holder.setLink(item, detailOperation)
    }
}

class LinkSearchListViewHolder(binding: ItemLinkSearchBinding) :
    RecyclerBasicViewHolder<ItemLinkSearchBinding>(binding) {

    fun setLink(
        link: SjLinksAndDomainsWithTags,
        detailOperation: (Int) -> Unit
    ) {
        binding.data = link
        binding.root.setOnClickListener { detailOperation(link.link.lid) }
        val drawable = when (link.link.type) {
            ELinkType.video -> R.drawable.ic_recycler_type_video
            ELinkType.link -> R.drawable.ic_recycler_type_link
        }
        Glide.with(itemView.context).load(drawable).into(binding.linkIconImageView)
    }

}