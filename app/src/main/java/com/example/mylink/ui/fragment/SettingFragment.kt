package com.example.mylink.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylink.R
import com.example.mylink.data.model.SettingData
import com.example.mylink.databinding.FragmentSettingBinding
import com.example.mylink.ui.adapter.RecyclerSettingAdapter
import com.example.mylink.ui.fragment.basic.SjBasicFragment
import com.example.mylink.ui.fragment.main.setting.domain.ListDomainFragment
import com.example.mylink.ui.fragment.main.setting.tag.ListTagFragment

class SettingFragment : SjBasicFragment<FragmentSettingBinding>() {
    private val tagFragment = ListTagFragment()
    private val domainFragment = ListDomainFragment()

    override fun layoutId(): Int = R.layout.fragment_setting

    override fun onCreateView() {
        binding.settingRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = RecyclerSettingAdapter()
        binding.settingRecyclerView.adapter = adapter
        adapter.setList(getSettingList())
    }

    private fun getSettingList(): List<SettingData> {
        return mutableListOf(
            SettingData("도메인 목록", ::moveToViewDomains),
            SettingData("태그 목록", ::moveToViewTags),
            SettingData("앱 정보 보기", ::moveToViewData),
        )
    }

    private fun moveToViewDomains() {
        this.moveToOtherFragment(domainFragment)
    }

    private fun moveToViewTags() {
        this.moveToOtherFragment(tagFragment)
    }

    private fun moveToViewData() {

    }
}