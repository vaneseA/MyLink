package com.example.mylink.ui.fragment.main.setting.domain

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.mylink.R

import com.example.mylink.databinding.FragmentEditDomainBinding
import com.example.mylink.ui.fragment.basic.SjBasicFragment
import com.example.mylink.viewmodel.domain.DomainViewModel


class EditDomainFragment : SjBasicFragment<FragmentEditDomainBinding>() {
    val viewModel: DomainViewModel by viewModels()

    companion object {
        fun newInstance(did: Int): EditDomainFragment {
            val fragment = EditDomainFragment()
            fragment.arguments = Bundle().apply { putInt("did", did) }
            return fragment
        }
    }


    // override methods
    override fun layoutId(): Int = R.layout.fragment_edit_domain

    override fun onCreateView() {
        binding.viewModel = viewModel
        if (arguments != null) {
            loadUpdateData(arguments!!)
            binding.toolbar.toolbarTitle = "도메인 수정"
        }
        binding.saveButton.setOnClickListener { saveDomain() }
    }


    // load and set update data
    private fun loadUpdateData(arguments: Bundle) {
        val did = arguments.getInt("did")
        viewModel.setDomain(did)
    }


    // save domain
    private fun saveDomain() {
        viewModel.saveDomain()
        popBack()
    }

}