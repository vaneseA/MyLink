package com.example.mylink.ui.activity.basic

import androidx.viewbinding.ViewBinding
import com.example.mylink.R

abstract class SjBasicActivity<T : ViewBinding> : ViewBindingBasicActivity<T>() {
    protected val RESULT_SUCCESS = 0
    protected val RESULT_FAILED = 1
    protected val RESULT_CANCELED = 2

    override fun fragmentContainer(): Int = R.id.fragmentContainer
}