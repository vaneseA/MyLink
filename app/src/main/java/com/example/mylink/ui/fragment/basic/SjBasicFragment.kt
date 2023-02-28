package com.example.mylink.ui.fragment.basic

import androidx.databinding.ViewDataBinding
import com.example.mylink.R


abstract class SjBasicFragment<T : ViewDataBinding> : DataBindingBasicFragment<T>() {
    protected val RESULT_SUCCESS = 0
    protected val RESULT_FAILED = 1
    protected val RESULT_CANCELED = 2

    override fun fragmentContainer(): Int = R.id.fragmentContainer

}