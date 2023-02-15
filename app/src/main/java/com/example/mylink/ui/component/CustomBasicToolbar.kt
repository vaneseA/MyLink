package com.example.mylink.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.example.mylink.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar

class CustomBasicToolbar(context: Context, attrs: AttributeSet?) : AppBarLayout(context, attrs) {

    var toolbarTitle = ""
        set(value) {
            field = value
            toolbar.setTitle(value)
            invalidate()
            requestLayout()
        }
    private val toolbar: MaterialToolbar = MaterialToolbar(context)

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomBasicToolbar,
            0, 0
        ).apply {
            try {
                toolbarTitle = getString(R.styleable.CustomBasicToolbar_toolbarTitle).toString()
            } finally {
                recycle()
            }
        }

        toolbar.layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, //width
            ViewGroup.LayoutParams.WRAP_CONTENT  //height
        )
        CustomComponentStyleUtil.setMaterialCustomToolbarStyle(toolbar)
        this.addView(toolbar)
    }

}