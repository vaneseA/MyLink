package com.example.mylink.ui.adapter.binding

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.example.mylink.data.model.FullNameTagValue
import com.example.mylink.data.model.SjTag
import com.example.mylink.ui.component.SjTagChip
import com.google.android.material.chip.ChipGroup

class DataBindingSjAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("chipDataList")
        fun setChipByList(view: ChipGroup, tags: List<SjTag>?) {
            view.removeAllViews()
            if (!tags.isNullOrEmpty()) {
                for( tag in tags) {
                    val chip = SjTagChip(view.context, tag)
                    chip.setViewMode()
                    view.addView(chip)
                }
            }
        }

        @JvmStatic
        @BindingAdapter("chipValueList")
        fun setChipByValueList(view: ChipGroup, tags: List<FullNameTagValue>?) {
            view.removeAllViews()
            if (!tags.isNullOrEmpty()) {
                for(value in tags) {
                    val chip = SjTagChip(view.context, value.tag)
                    chip.setViewMode()
                    chip.setText(value.fullName)
                    view.addView(chip)
                }
            }
        }

        @JvmStatic
        @BindingAdapter("chipCheckableDataList")
        fun setCheckableChipByList(view: ChipGroup, tags: List<SjTag>?) {
            view.removeAllViews()
            if (!tags.isNullOrEmpty()) {
                for( tag in tags) {
                    val chip = SjTagChip(view.context, tag)
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