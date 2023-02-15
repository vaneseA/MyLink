package com.example.mylink.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import com.example.mylink.data.model.SjDomain
import com.example.mylink.databinding.ItemDomainsBinding
import com.example.mylink.ui.adapter.basic.SjDataBindingViewHolder
import com.example.mylink.ui.adapter.basic.SjRecyclerAdapter

class RecyclerDomainAdapter(
    private val updateOperation: (Int) -> Unit,
    private val deleteOperation: (SjDomain) -> Unit
) : SjRecyclerAdapter<SjDomain, DomainsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DomainsViewHolder {
        val binding = ItemDomainsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DomainsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DomainsViewHolder, item: SjDomain) {
        holder.setItem(item,updateOperation, deleteOperation)
    }

}

class DomainsViewHolder(binding: ItemDomainsBinding) :
    SjDataBindingViewHolder<ItemDomainsBinding>(binding) {

    fun setItem(
        domain: SjDomain,
        updateOperation: (Int) -> Unit,
        deleteOperation: (SjDomain) -> Unit
    ) {
        binding.domain = domain
        binding.deleteImageView.setOnClickListener { deleteOperation(domain) }
        binding.editImageView.setOnClickListener { updateOperation(domain.did) }
    }

}