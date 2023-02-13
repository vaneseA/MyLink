package com.example.mylink.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mylink.data.SjLink
import com.example.mylink.data.SjLinkAndDomain
import com.example.mylink.databinding.ItemLinksBinding


class LinksAdapter(
    private val openOperation: (String) -> Unit,
    private val deleteOperation: (SjLink) -> Unit
) :
    RecyclerView.Adapter<LinksViewHolder>() {
    var itemList: List<SjLinkAndDomain> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinksViewHolder {
        val binding = ItemLinksBinding.inflate(LayoutInflater.from(parent.context))
        return LinksViewHolder(binding);
    }

    override fun onBindViewHolder(holder: LinksViewHolder, position: Int) {
        holder.setLink(itemList[position], openOperation, deleteOperation)
    }

    override fun getItemCount(): Int = itemList.size

}

class LinksViewHolder(private val binding: ItemLinksBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private lateinit var item: SjLinkAndDomain

    fun setLink(
        item: SjLinkAndDomain,
        openOperation: (String) -> Unit,
        deleteOperation: (SjLink) -> Unit
    ) {
        this.item = item
        Log.d("아이템 내용", item.toString())
        binding.linksItemDomainTextView.setText(item.domain.name)
        binding.linksItemNameTextView.setText(item.link.name)
        binding.linksItemWebButton.setOnClickListener { openOperation("${item.domain.url}${item.link.url}") }
        binding.linksItemEditButton.setOnClickListener { editLink() }
        binding.linksItemDeleteButton.setOnClickListener { deleteOperation(item.link) }
    }

    private fun editLink() {

    }

}