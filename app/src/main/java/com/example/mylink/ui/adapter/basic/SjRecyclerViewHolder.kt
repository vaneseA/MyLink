package com.example.mylink.ui.adapter.basic

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class SjDataBindingViewHolder<T:ViewDataBinding>(protected val binding: T) :
    RecyclerView.ViewHolder(binding.root)