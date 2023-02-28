package com.example.mylink.ui.fragment.main.setting.domain

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylink.R
import com.example.mylink.data.model.SjDomain
import com.example.mylink.databinding.FragmentListDomainBinding
import com.example.mylink.ui.adapter.recycler.DomainListAdapter
import com.example.mylink.ui.fragment.basic.SjBasicFragment
import com.example.mylink.viewmodel.domain.DomainViewModel

class ListDomainFragment : SjBasicFragment<FragmentListDomainBinding>() {
    val viewModel: DomainViewModel by activityViewModels()


    // override methods
    override fun layoutId(): Int = R.layout.fragment_list_domain

    override fun onCreateView() {
        val handlerMap = hashMapOf<Int, ()->Unit>(R.id.menu_add to ::moveToAddFragment)
        binding.toolbar.setMenu(R.menu.toolbar_menu_add, handlerMap = handlerMap)
        // set recyclerView
        val adapter = DomainListAdapter(
            ::moveToEditDomainFragment,
            ::deleteDomain
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // set domain item list
//        viewModel.domains.observe(
//            viewLifecycleOwner, {
//                if (it.isEmpty()) {
//                    binding.include.emptyView.visibility = View.VISIBLE
//                } else {
//                    binding.include.emptyView.visibility = View.GONE
//                }
//                adapter.setList(it)
//            }
//        )
    }


    // handle user click event
    private fun moveToEditDomainFragment(did: Int) {
        val fragment = EditDomainFragment.newInstance(did)
        moveToOtherFragment(fragment)
    }

    private fun deleteDomain(domain: SjDomain) {
        viewModel.deleteDomain(domain)
    }

    private fun moveToAddFragment(){
        moveToOtherFragment(EditDomainFragment())
    }

}