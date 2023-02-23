package com.example.mylink.ui.component

import android.util.TypedValue
import androidx.core.content.ContextCompat
import com.example.mylink.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip

class CustomComponentStyleUtil {
//
    companion object{
        fun setMaterialCustomToolbarStyle(toolbar:MaterialToolbar){
            val context = toolbar.context
            val typedValue = TypedValue()
            val backgroundColor = context.obtainStyledAttributes(
                typedValue.data,
                arrayOf(R.attr.colorSurface).toIntArray()
            )
            val textColor = context.obtainStyledAttributes(
                typedValue.data,
                arrayOf(R.attr.colorOnBackground).toIntArray()
            )

            toolbar.setBackgroundColor(backgroundColor.getColor(0,0))
            toolbar.setTitleTextColor(textColor.getColor(0,0))
        }

        fun setMaterialCustomChipStyle(chip: Chip){
            chip.setChipBackgroundColorResource(R.color.color_chip_background)
            chip.setTextColor(ContextCompat.getColorStateList(chip.context,R.color.color_chip_text))
        }

    }
}