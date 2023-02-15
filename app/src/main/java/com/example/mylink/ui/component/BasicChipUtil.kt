package com.example.mylink.ui.component

import androidx.core.content.ContextCompat
import com.example.mylink.R
import com.google.android.material.chip.Chip

class BasicChipUtil {

    companion object{
        fun setBasicColorStyle(chip:Chip){
            chip.setChipBackgroundColorResource(R.color.color_chip_background)
            chip.setTextColor(ContextCompat.getColorStateList(chip.context,R.color.color_chip_text))
        }
    }
}