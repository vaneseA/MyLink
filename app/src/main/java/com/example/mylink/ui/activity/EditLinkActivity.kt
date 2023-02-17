package com.example.mylink.ui.activity

import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.example.mylink.databinding.ActivityEditLinkBinding
import com.example.mylink.ui.activity.basic.SjBasicActivity
import com.example.mylink.ui.fragment.EditLinkFragment


class EditLinkActivity : SjBasicActivity<ActivityEditLinkBinding>() {

    override fun viewBindingInflate(inflater: LayoutInflater): ActivityEditLinkBinding =
        ActivityEditLinkBinding.inflate(layoutInflater)

    override fun homeFragment(): Fragment {
        val lid = intent.getIntExtra("lid", -1)
        return if (lid == -1) {
            EditLinkFragment()
        } else {
            EditLinkFragment.newInstance(lid)
        }
    }

    override fun onCreate() {}

}