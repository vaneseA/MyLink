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
import com.example.mylink.viewmodel.TagViewModel

class EditTagFragment : DataBindingBasicFragment<FragmentEditTagBinding>() {
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

    override fun layoutId(): Int = R.layout.fragment_edit_tag

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel=viewModel
        if(arguments!=null){
            val tid = arguments!!.getInt("tid")
            viewModel.setTag(tid)
        }
        binding.nameEdtiText.requestFocus()
        binding.saveButton.setOnClickListener { insertTag() }
        return binding.root
    }

    private fun insertTag() {
        viewModel.saveTag()
        popBack()
    }

}