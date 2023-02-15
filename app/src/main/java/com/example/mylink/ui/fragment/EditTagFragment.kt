package com.example.mylink.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mylink.R
import com.example.mylink.data.model.SjTag
import com.example.mylink.databinding.FragmentEditTagBinding
import com.example.mylink.ui.fragment.basic.DataBindingBasicFragment
import com.example.mylink.ui.fragment.basic.SjBasicFragment
import com.example.mylink.viewmodel.TagViewModel

class EditTagFragment : SjBasicFragment<FragmentEditTagBinding>() {
    val viewModel: TagViewModel by viewModels()

    companion object {
        fun newInstance(tag: SjTag): EditTagFragment {
            val fragment = EditTagFragment()
            fragment.arguments = Bundle().apply {
                putInt("tid", tag.tid)
            }
            return fragment
        }
    }


    // override methods
    override fun layoutId(): Int = R.layout.fragment_edit_tag

    override fun onCreateView() {
        binding.viewModel = viewModel
        if (arguments != null) {
            loadUpdateData(arguments!!)
            binding.toolbar.toolbarTitle="태그 수정"
        }

        //set focus
        binding.nameEdtiText.requestFocus()

        // handle user click event
        binding.saveButton.setOnClickListener { saveTag() }
    }


    // load and set update data
    private fun loadUpdateData(arguments: Bundle) {
        val tid = arguments.getInt("tid")
        viewModel.setTag(tid)
    }

    // save Tag
    private fun saveTag() {
        viewModel.saveTag()
        popBack()
    }

}