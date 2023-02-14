package com.example.mylink.ui.activity

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.mylink.databinding.ActivityViewLinkBinding
import com.example.mylink.ui.activity.basic.ViewBindingBasicActivity
import com.example.mylink.ui.fragment.ViewLinkFragment
import com.example.mylink.viewmodel.ReadLinkViewModel

class ViewLinkActivity : ViewBindingBasicActivity<ActivityViewLinkBinding>() {
    val viewModel: ReadLinkViewModel by viewModels()
    private val homeFragment = ViewLinkFragment()

    override fun viewBindingInflate(inflater: LayoutInflater): ActivityViewLinkBinding =
        ActivityViewLinkBinding.inflate(inflater)

    override fun homeFragment(): Fragment = homeFragment
}