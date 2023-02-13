package com.example.mylink.ui.activity

import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.example.mylink.databinding.ActivityEditLinkBinding
import com.example.mylink.ui.activity.basic.ViewBindingBasicActivity
import com.example.mylink.ui.fragment.EditLinkFragment


class EditLinkActivity : ViewBindingBasicActivity<ActivityEditLinkBinding>() {
    override fun viewBindingInflate(inflater: LayoutInflater): ActivityEditLinkBinding =
        ActivityEditLinkBinding.inflate(layoutInflater)

    override fun homeFragment(): Fragment = EditLinkFragment()

}