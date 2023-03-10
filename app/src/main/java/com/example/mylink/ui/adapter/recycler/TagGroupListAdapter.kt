package com.example.mylink.ui.adapter.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import com.example.mylink.R
import com.example.mylink.data.model.SjTagGroup
import com.example.mylink.data.model.SjTagGroupWithTags
import com.example.mylink.databinding.ItemTagGroupBinding
import com.example.mylink.ui.adapter.basic.RecyclerBasicAdapter
import com.example.mylink.ui.adapter.basic.RecyclerBasicViewHolder

class TagGroupListAdapter(
    private val deleteOperation: (Int) -> Unit,
    private val editOperation: (Int) -> Unit,
    private val renameOperation: (SjTagGroup) -> Unit
) : RecyclerBasicAdapter<SjTagGroupWithTags, TagGroupListViewHolder>() {
    override fun onBindViewHolder(holder: TagGroupListViewHolder, item: SjTagGroupWithTags) {
        holder.setItem(item, deleteOperation, editOperation, renameOperation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagGroupListViewHolder {
        val binding =
            ItemTagGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TagGroupListViewHolder(binding)
    }
}

class TagGroupListViewHolder(binding: ItemTagGroupBinding) :
    RecyclerBasicViewHolder<ItemTagGroupBinding>(binding) {

    fun setItem(
        item: SjTagGroupWithTags,
        deleteOperation: (Int) -> Unit,
        editOperation: (Int) -> Unit,
        renameOperation: (SjTagGroup) -> Unit,
    ) {
        binding.item = item

        val popupMenu = PopupMenu(itemView.context, binding.swapImageView)
        popupMenu.inflate(R.menu.popup_menu_tag_group)
        popupMenu.setOnMenuItemClickListener {
            val gid = item.tagGroup.gid
            when (it.itemId) {
                R.id.menu_group_delete -> {
                    deleteOperation(gid)
                    true
                }
                R.id.menu_group_edit -> {
                    editOperation(gid)
                    true
                }
                R.id.menu_group_rename -> {
                    renameOperation(item.tagGroup)
                    true
                }
                else -> false
            }
        }

        if (item.tagGroup.gid == 1) {
            binding.swapImageView.visibility = View.INVISIBLE
        } else {
            binding.swapImageView.visibility = View.VISIBLE
            binding.swapImageView.setOnClickListener {
                popupMenu.show()
            }
        }

        if (item.tags.isEmpty()) {
            binding.emptyTextView.visibility = View.VISIBLE
        } else {
            binding.emptyTextView.visibility = View.GONE
        }

        if (item.tagGroup.isPrivate) {
            binding.privateImageView.visibility = View.VISIBLE
        } else {
            binding.privateImageView.visibility = View.GONE
        }


    }

}