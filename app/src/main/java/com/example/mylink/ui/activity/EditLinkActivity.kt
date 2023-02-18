package com.example.mylink.ui.activity

import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.example.mylink.databinding.ActivityEditLinkBinding
import com.example.mylink.ui.activity.basic.SjBasicActivity
import com.example.mylink.ui.fragment.edit_link.EditLinkAndVideoFragment
import com.example.mylink.ui.fragment.edit_link.EditLinkPasteFragment


class EditLinkActivity : SjBasicActivity<ActivityEditLinkBinding>() {


    override fun viewBindingInflate(inflater: LayoutInflater): ActivityEditLinkBinding =
        ActivityEditLinkBinding.inflate(layoutInflater)

    override fun homeFragment(): Fragment {
        val lid = intent.getIntExtra("lid", -1)
        val url = intent.getStringExtra("url") ?: ""
        return if (lid == -1 && url.isEmpty()) {
            EditLinkPasteFragment()
        } else {
            EditLinkAndVideoFragment.newInstance(lid, url)
        }
    }

    override fun onCreate() {}
//    override fun homeFragment(): Fragment = EditPasteFragment()


}