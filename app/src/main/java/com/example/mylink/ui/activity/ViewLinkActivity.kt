package com.example.mylink.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.mylink.databinding.ActivityViewLinkBinding
import com.example.mylink.ui.activity.basic.ViewBindingBasicActivity
import com.example.mylink.ui.fragment.EmptyFragment
import com.example.mylink.ui.fragment.ViewLinkFragment
import com.example.mylink.viewmodel.ListMode
import com.example.mylink.viewmodel.ReadLinkViewModel

class ViewLinkActivity : ViewBindingBasicActivity<ActivityViewLinkBinding>() {
    val viewModel: ReadLinkViewModel by viewModels()
    private val homeFragment = ViewLinkFragment()
    private val emptyFragment = EmptyFragment()

    override fun viewBindingInflate(inflater: LayoutInflater): ActivityViewLinkBinding =
        ActivityViewLinkBinding.inflate(inflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.floatingActionView.setOnClickListener { startEditActivity() }
        viewModel.linkList.observe(this, {
            if (viewModel.mode == ListMode.MODE_ALL) {
                if (it.isEmpty()) {
                    setHomeFragment(emptyFragment)
                } else {
                    setHomeFragment(homeFragment)
                }
            }
        })

        viewModel.searchLinkList.observe(this, {
            if (viewModel.mode == ListMode.MODE_SEARCH) {
                if (it.isEmpty()) {
                    setHomeFragment(emptyFragment)
                } else {
                    setHomeFragment(homeFragment)
                }
            }
        })

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                end: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.searchLinkByLinkName(binding.searchEditText.text.toString())
                Log.d("typed", binding.searchEditText.text.toString())
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

    }

    private fun startEditActivity() {
        val intent = Intent(this, EditLinkActivity::class.java)
        startActivity(intent)
    }

    override fun homeFragment(): Fragment = homeFragment
}