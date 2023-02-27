package com.example.mylink.ui.fragment.basic

import androidx.databinding.ViewDataBinding
import com.example.mylink.R


abstract class SjBasicFragment<T : ViewDataBinding> : DataBindingBasicFragment<T>() {

    override fun fragmentContainer(): Int = R.id.fragmentContainer

}