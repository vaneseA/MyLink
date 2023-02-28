package com.example.mylink.ui.activity

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.mylink.databinding.ActivityPlainBinding

import com.example.mylink.ui.activity.basic.SjBasicActivity
import com.example.mylink.ui.fragment.edit_link.EditLinkFragment
import com.example.mylink.ui.fragment.edit_link.EditLinkPasteFragment
import com.example.mylink.viewmodel.edit_link.EditLinkViewModel


class EditLinkActivity : SjBasicActivity<ActivityPlainBinding>() {

    private val editPasteFragment = EditLinkPasteFragment()
    private val editFragment = EditLinkFragment()
    private val editViewModel: EditLinkViewModel by viewModels()

    override fun viewBindingInflate(inflater: LayoutInflater): ActivityPlainBinding =
        ActivityPlainBinding.inflate(layoutInflater)

    override fun homeFragment(): Fragment {
        val lid = intent.getIntExtra("lid", -1)
        val url = intent.getStringExtra("url") ?: ""
        when {
            lid != -1 -> editViewModel.setLinkByLid(lid)
            url.isNotEmpty() -> editViewModel.createLinkByUrl(url)
            else -> return editPasteFragment
        }
        return editFragment
    }

    override fun onCreate() {}


}