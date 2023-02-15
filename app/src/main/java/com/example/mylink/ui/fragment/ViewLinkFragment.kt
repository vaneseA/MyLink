package com.example.mylink.ui.fragment

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylink.R
import com.example.mylink.data.model.SjLink
import com.example.mylink.data.model.SjTag
import com.example.mylink.databinding.FragmentViewLinkBinding
import com.example.mylink.ui.activity.EditLinkActivity
import com.example.mylink.ui.adapter.RecyclerLinkAdapter
import com.example.mylink.ui.fragment.basic.SjBasicFragment
import com.example.mylink.viewmodel.ListMode
import com.example.mylink.viewmodel.ReadLinkViewModel

class ViewLinkFragment : SjBasicFragment<FragmentViewLinkBinding>() {
    val viewModel: ReadLinkViewModel by activityViewModels()


    // override methods
    override fun layoutId(): Int = R.layout.fragment_view_link

    override fun onResume() {
        super.onResume()
        viewModel.searchLinkBySearchSet()
    }

    override fun onCreateView() {
        // set recycler view
        val adapter = RecyclerLinkAdapter(
            openOperation = ::startWebBrowser,
            updateOperation = ::startEditActivityToUpdate,
            deleteOperation = ::deleteLink
        )
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)


        // set list when Mode ALL
        viewModel.linkList.observe(viewLifecycleOwner,
            {
                if (viewModel.mode == ListMode.MODE_ALL) {
                    adapter.setList(it)
                    if (it.isEmpty()) {
                        binding.include.emptyView.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    } else {
                        binding.include.emptyView.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                    }
                }
            }
        )

        // set list when Mode SEARCH
        viewModel.searchLinkList.observe(viewLifecycleOwner,
            {
                if (viewModel.mode == ListMode.MODE_SEARCH) {
                    adapter.setList(it)
                    if (it.isEmpty()) {
                        binding.include.emptyView.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    } else {
                        binding.include.emptyView.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                    }

                }
            }
        )


        // handle user click event
        binding.floatingActionView.setOnClickListener { startEditActivity() }
        binding.searchEditText.setOnClickListener {
            this.moveToSearchFragment()
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

    private fun isStringTypeUrl(url: String): Boolean {
        return url.startsWith("http://") || url.startsWith("https://")
    }
    private fun startWebBrowser(url: String) {
        if (isStringTypeUrl(url)) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun startEditActivity() {
        val intent = Intent(requireContext(), EditLinkActivity::class.java)
        startActivity(intent)
    }

    private fun startEditActivityToUpdate(lid: Int) {
        val intent = Intent(requireContext(), EditLinkActivity::class.java)
        intent.putExtra("lid", lid)
        startActivity(intent)
    }

}