package com.example.mylink.ui.fragment

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.example.mylink.R

import com.example.mylink.databinding.FragmentEditDomainBinding
import com.example.mylink.ui.fragment.basic.SjBasicFragment
import com.example.mylink.viewmodel.DomainViewModel


class EditDomainFragment : SjBasicFragment<FragmentEditDomainBinding>() {
    val viewModel: DomainViewModel by activityViewModels()

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