package com.example.mylink.ui.fragment.main.search

import android.content.Intent
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mylink.R
import com.example.mylink.data.model.SjLinksAndDomainsWithTags
import com.example.mylink.databinding.FragmentListLinkBinding
import com.example.mylink.ui.activity.EditLinkActivity
import com.example.mylink.ui.adapter.recycler.LinkSearchListAdapter
import com.example.mylink.ui.component.DataState
import com.example.mylink.ui.component.ViewVisibilityUtil
import com.example.mylink.ui.fragment.basic.SjBasicFragment
import com.example.mylink.ui.fragment.main.search.detail_link.DetailLinkFragment
import com.example.mylink.viewmodel.SettingViewModel
import com.example.mylink.viewmodel.detail_link.DetailLinkViewModel
import com.example.mylink.viewmodel.search.SearchLinkViewModel
import kotlinx.coroutines.*

class ListLinkFragment : SjBasicFragment<FragmentListLinkBinding>() {
    private val viewModel: SearchLinkViewModel by activityViewModels()
    private val settingViewModel: SettingViewModel by activityViewModels()

    // fragments
    private val detailFragment = DetailLinkFragment()
    private val detailViewModel: DetailLinkViewModel by activityViewModels()
    private val searchFragment = SearchFragment()

    // control view visibility
    private lateinit var viewUtil: ViewVisibilityUtil

    // for recyclerView
    private lateinit var adapter: LinkSearchListAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun layoutId(): Int = R.layout.fragment_list_link

    override fun onStart() {
        super.onStart()
        viewUtil.state = DataState.LOADING
        viewModel.isPrivateMode=settingViewModel.isPrivateMode.value ?: false
        viewModel.refreshData()
    }

    override fun onCreateView() {
        // set binding variable
        binding.viewModel = viewModel

        settingViewModel.isPrivateMode.observe(viewLifecycleOwner){
            viewModel.isPrivateMode = it
        }

        // set recycler view
        initRecyclerView()

        // set view Util
        viewUtil = ViewVisibilityUtil(
            loadingView = binding.shimmer,
            loadedView = binding.recyclerView,
            emptyView = binding.emptyGroup
        )

        // handle user click event
        setOnClickListeners()

        // set adapter list
        viewModel.links.observe(viewLifecycleOwner) {
            if (it != null) setAdapterList(it)
        }
    }

    private fun setAdapterList(list: List<SjLinksAndDomainsWithTags>) {
        CoroutineScope(Dispatchers.Main).launch {
            adapter.setList(list)
            delay(500)
            if (list.isEmpty()) {
                viewUtil.state = DataState.EMPTY
            } else {
                viewUtil.state = DataState.LOADED
            }
        }
    }

    override fun initRecyclerView() {
        this.adapter = LinkSearchListAdapter(detailOperation = ::moveToDetailFragment)
        this.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = this.adapter
        binding.recyclerView.layoutManager = this.layoutManager
    }


    // functions for user event
    override fun setOnClickListeners() {
        binding.floatingActionView.setOnClickListener { startEditActivity() }
        binding.searchEditText.setOnClickListener { moveToSearchFragment() }
        binding.cancelSearchSetImageView.setOnClickListener { viewModel.clearSearchSet() }
    }

    private fun moveToSearchFragment() {
        moveToOtherFragment(searchFragment)
    }

    private fun moveToDetailFragment(lid: Int) {
        detailViewModel.lid = lid
        moveToOtherFragment(detailFragment)
    }

    private fun startEditActivity() {
        val intent = Intent(requireContext(), EditLinkActivity::class.java)
        startActivity(intent)
    }


}