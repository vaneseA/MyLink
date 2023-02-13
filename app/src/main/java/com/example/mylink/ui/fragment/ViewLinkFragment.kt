package com.example.mylink.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylink.R
import com.example.mylink.data.SjLink
import com.example.mylink.data.SjTag
import com.example.mylink.databinding.FragmentViewLinkBinding
import com.example.mylink.ui.adapter.LinksAdapter
import com.example.mylink.ui.fragment.basic.DataBindingBasicFragment
import com.example.mylink.viewmodel.ListMode
import com.example.mylink.viewmodel.ReadLinkViewModel

class ViewLinkFragment : DataBindingBasicFragment<FragmentViewLinkBinding>() {
    val viewModel: ReadLinkViewModel by activityViewModels()

    override fun layoutId(): Int = R.layout.fragment_view_link

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val adapter = LinksAdapter(::startWebBrowser, ::deleteLink)
        viewModel.linkList.observe(this,
            {
                if (viewModel.mode == ListMode.MODE_ALL) {
                    adapter.itemList = it
                    adapter.notifyDataSetChanged()
                }
            }
        )

        viewModel.searchLinkList.observe(this,
            {
                if (viewModel.mode == ListMode.MODE_SEARCH) {
                    adapter.itemList = it
                    adapter.notifyDataSetChanged()
                }
            }
        )

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    private fun deleteLink(link: SjLink, tags: List<SjTag>) {
        viewModel.deleteLink(link, tags)
    }

    private fun editLink() {}

    private fun shareLink() {}

    private fun startWebBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

}