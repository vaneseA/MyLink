package com.example.mylink.ui.fragment

import android.content.Intent
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylink.R
import com.example.mylink.data.model.SjLink
import com.example.mylink.data.model.SjLinksAndDomainsWithTags
import com.example.mylink.data.model.SjTag
import com.example.mylink.databinding.FragmentListLinkBinding
import com.example.mylink.ui.activity.EditLinkActivity
import com.example.mylink.ui.adapter.RecyclerSearchLinkAdapter
import com.example.mylink.ui.component.DataState
import com.example.mylink.ui.component.ViewVisibilityUtil
import com.example.mylink.ui.fragment.basic.SjBasicFragment
import com.example.mylink.ui.fragment.search.SearchFragment
import com.example.mylink.viewmodel.search.ListMode
import com.example.mylink.viewmodel.search.SearchLinkViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListLinkFragment : SjBasicFragment<FragmentListLinkBinding>() {
    val viewModel: SearchLinkViewModel by activityViewModels()

    lateinit var viewUtil: ViewVisibilityUtil

    // override methods
    override fun layoutId(): Int = R.layout.fragment_list_link

    override fun onResume() {
        super.onResume()
        viewUtil.state = DataState.LOADING
        binding.shimmer.startShimmer()
        viewModel.searchLinkBySearchSet()
    }

    override fun onCreateView() {
        // set recycler view
        val adapter = RecyclerSearchLinkAdapter(
            detailOperation = ::moveToDetailFragment
        )
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        viewUtil = ViewVisibilityUtil(
            loadingView = binding.shimmerRecylerView,
            loadedView = binding.recyclerView,
            emptyView = binding.include.emptyView
        )

        // set list when Mode ALL
        viewModel.linkList.observe(viewLifecycleOwner,
            {
                if (viewModel.mode == ListMode.MODE_ALL) {
                    adapter.setList(it)
                    delayAndViewVisibleControl(it)
                }
            }
        )

        // set list when Mode SEARCH
        viewModel.searchLinkList.observe(viewLifecycleOwner,
            {
                if (viewModel.mode == ListMode.MODE_SEARCH) {
                    adapter.setList(it)
                    delayAndViewVisibleControl(it)
                }
            }
        )


        // handle user click event
        binding.floatingActionView.setOnClickListener { startEditActivity() }
        binding.searchEditText.setOnClickListener {
            this.moveToSearchFragment()
        }
    }

    private fun delayAndViewVisibleControl(datas: List<SjLinksAndDomainsWithTags>) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(1500)
            if (datas.isEmpty()) {
                viewUtil.state = DataState.EMPTY
            } else {
                viewUtil.state = DataState.LOADED
            }
        }
    }

    // handle user event methods
    private fun deleteLink(link: SjLink, tags: List<SjTag>) {
        viewModel.deleteLink(link, tags)
        if (viewModel.mode == ListMode.MODE_SEARCH) {
            viewModel.searchLinkBySearchSetAndSave()
        }
    }

    private fun moveToSearchFragment() {
        moveToOtherFragment(SearchFragment())
    }

    private fun moveToDetailFragment(lid: Int) {
        moveToOtherFragment(DetailLinkFragment.newInstance(lid))
    }


    private fun startEditActivity() {
        val intent = Intent(requireContext(), EditLinkActivity::class.java)
        startActivity(intent)
    }


}