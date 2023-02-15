package com.example.mylink.ui.component

import android.content.Context
import com.example.mylink.data.model.SjTag
import com.google.android.material.chip.Chip

class SjTagChip(context: Context, val tag: SjTag) : Chip(context, null) {

    init {
        setText(tag.name)
        setBasicMode()
        isCheckedIconVisible = false

        CustomComponentStyleUtil.setMaterialCustomChipStyle(this)
    }

    fun setBasicMode() {
        isCheckable = true
        id = tag.tid
    }

    fun setEditMode(
        deleteOperation: (SjTag) -> Unit,
        editOperation: (SjTag) -> Unit
    ) {
        isCheckable = false
        isCloseIconVisible = true
        setOnCloseIconClickListener {
            deleteOperation(tag)
        }
        setOnLongClickListener {
            editOperation(tag)
            true
        }
    }

}