package com.example.mylink.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.example.mylink.data.model.SjTag
import com.example.mylink.ui.component.SjTagChip
import com.google.android.material.chip.ChipGroup

class DataBindingSjAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("chipCountData")
        fun setTextByCount(view: TextView, count: Int) {
            if (count < 2) {
                view.isVisible = false
            } else {
                view.isVisible = true
                view.setText("및 ${count - 1}개")
            }
        }

        @JvmStatic
        @BindingAdapter("chipDataList")
        fun setChipByList(view: ChipGroup, tags: List<SjTag>?) {
            view.removeAllViews()
            if (!tags.isNullOrEmpty()) {
                for( tag in tags) {
                    val chip = SjTagChip(view.context, tag)
                    chip.isChecked = true
                    chip.isCheckable = false
                    chip.isClickable = false
                    view.addView(chip)
                }
            }
        }

        @JvmStatic
        @BindingAdapter("textOrHide")
        fun setTextOrHide(view: TextView, string: String) {
            if (string.isEmpty()) {
                view.visibility = View.GONE
            } else {
                view.setText(string)
                view.visibility = View.VISIBLE
            }

        }
    }
}