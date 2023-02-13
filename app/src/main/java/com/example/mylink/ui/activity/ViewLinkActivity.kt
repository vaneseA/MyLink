package com.example.mylink.ui.activity

import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.example.mylink.databinding.ActivityViewLinkBinding
import com.example.mylink.ui.activity.basic.ViewBindingBasicActivity
import com.example.mylink.ui.fragment.ViewLinkFragment

class ViewLinkActivity : ViewBindingBasicActivity<ActivityViewLinkBinding>() {
    override fun viewBindingInflate(inflater: LayoutInflater): ActivityViewLinkBinding
            = ActivityViewLinkBinding.inflate(inflater)

    override fun homeFragment(): Fragment = ViewLinkFragment()
}