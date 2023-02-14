package com.example.mylink.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylink.R
import com.example.mylink.data.model.SjDomain
import com.example.mylink.databinding.FragmentViewDomainBinding
import com.example.mylink.ui.adapter.DomainsAdapter
import com.example.mylink.ui.fragment.basic.DataBindingBasicFragment
import com.example.mylink.viewmodel.DomainViewModel

class ViewDomainFragment :DataBindingBasicFragment<FragmentViewDomainBinding>(){
    val viewModel : DomainViewModel by activityViewModels()

    override fun layoutId(): Int  = R.layout.fragment_view_domain

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DomainsAdapter(
            listOf(),
            ::moveToEditDomainFragment,
            ::deleteDomain
        )

        viewModel.domains.observe(
            viewLifecycleOwner,{
                if(it.isEmpty()){
                    binding.include.emptyView.visibility=View.VISIBLE
                }else{
                    binding.include.emptyView.visibility=View.GONE
                }
                adapter.setList(it)
            }
        )

        binding.recyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.recyclerView.adapter=adapter
    }

    private fun moveToEditDomainFragment(did: Int){
        val fragment = EditDomainFragment.newInstance(did)
        moveToOtherFragment(fragment)
    }
    private fun deleteDomain(domain:SjDomain){
        viewModel.deleteDomain(domain)
    }


}