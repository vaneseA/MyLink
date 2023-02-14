package com.example.mylink.ui.fragment

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.CompoundButton
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylink.R
import com.example.mylink.data.model.SjSearch
import com.example.mylink.data.model.SjTag
import com.example.mylink.databinding.FragmentSearchBinding
import com.example.mylink.ui.component.SjTagChip
import com.example.mylink.ui.fragment.basic.DataBindingBasicFragment
import com.example.mylink.viewmodel.ReadLinkViewModel
import com.example.mylink.viewmodel.SearchViewModel
import com.github.yeeun_yun97.toy.linksaver.ui.adapter.SearchesAdapter


class SearchFragment : DataBindingBasicFragment<FragmentSearchBinding>() {

    val viewModel: SearchViewModel by activityViewModels()
    val readLinkViewModel: ReadLinkViewModel by activityViewModels()

    override fun layoutId(): Int = R.layout.fragment_search

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.searchEditText.requestFocus()

        viewModel.tagList.observe(viewLifecycleOwner, Observer {
            val onCheckedListener = object : CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(btn: CompoundButton?, isChecked: Boolean) {
                    val chip = btn as SjTagChip
                    if (isChecked) {
                        viewModel.selectedTags.add(chip.tag)
                    } else {
                        viewModel.selectedTags.remove(chip.tag)
                    }
                }
            }
            binding.tagChipGroup.removeAllViews()
            for (tag in it) {
                val tag = SjTagChip(requireContext(), tag)
                tag.setOnCheckedChangeListener(onCheckedListener)
                binding.tagChipGroup.addView(tag)
            }
        })

        binding.searchEditText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(
                TextView: TextView?,
                actionId: Int,
                keyEvent: KeyEvent?
            ): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search(binding.searchEditText.text.toString())
                }
                return false
            }
        })



        binding.recentSearchedRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = SearchesAdapter(::setSearch)
        binding.recentSearchedRecyclerView.adapter = adapter
        viewModel.searchList.observe(viewLifecycleOwner,
            {
                adapter.itemList = it
                adapter.notifyDataSetChanged()
            }
        )


        binding.deleteImageView.setOnClickListener { deleteAllSearch() }
        binding.deleteTextView.setOnClickListener { deleteAllSearch() }
        return binding.root
    }

    private fun deleteAllSearch(){
        viewModel.deleteAllSearch()
    }

    private fun setSearch(keyword: String, tags: List<SjTag>) {
        viewModel.selectedTags.clear()
        binding.tagChipGroup.clearCheck()
        viewModel.selectedTags.addAll(tags)
        this.search(keyword)
    }

    private fun search(keyword: String) {
        readLinkViewModel.searchLinkByLinkName(keyword)
        viewModel.saveSearch(SjSearch(keyword = keyword))
        popBack()
    }

}