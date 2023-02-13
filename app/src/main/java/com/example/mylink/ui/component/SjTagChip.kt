package com.example.mylink.ui.component

import android.content.Context
import com.example.mylink.data.SjTag
import com.google.android.material.chip.Chip

class SjTagChip(context: Context, val tag: SjTag) : Chip(context) {

    init{
        setText(tag.name)
        isCheckable=true
        id=tag.tid
    }

}