package com.example.mylink.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mylink.R
import com.example.mylink.data.SjDomain
import com.example.mylink.databinding.FragmentEditDomainBinding
import com.example.mylink.ui.fragment.basic.DataBindingBasicFragment
import com.example.mylink.viewmodel.CreateDomainViewModel

class EditDomainFragment : DataBindingBasicFragment<FragmentEditDomainBinding>() {
    val viewModel: CreateDomainViewModel by viewModels()

    override fun layoutId(): Int = R.layout.fragment_edit_domain

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding.saveButton.setOnClickListener {
            saveDomain()
        }
        return binding.root
    }


    private fun saveDomain() {
        viewModel.insertDomain(
            SjDomain(
                name = binding.nameEdtiText.text.toString(),
                url = binding.urlEditText.text.toString()
            )
        )
        parentFragmentManager.popBackStack()
    }
}