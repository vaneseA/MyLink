package com.example.mylink.ui.activity.basic

import androidx.viewbinding.ViewBinding
import com.example.mylink.R

abstract class SjBasicActivity<T : ViewBinding> : ViewBindingBasicActivity<T>() {
    override fun fragmentContainer(): Int = R.id.fragmentContainer
}