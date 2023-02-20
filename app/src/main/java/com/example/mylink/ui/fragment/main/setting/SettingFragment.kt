package com.example.mylink.ui.fragment.main.setting

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylink.R
import com.example.mylink.data.model.SettingItemValue
import com.example.mylink.databinding.FragmentSettingBinding
import com.example.mylink.ui.adapter.RecyclerSettingAdapter
import com.example.mylink.ui.fragment.basic.SjBasicFragment
import com.example.mylink.ui.fragment.main.setting.app_info.AppInfoFragment
import com.example.mylink.ui.fragment.main.setting.domain.ListDomainFragment
import com.example.mylink.ui.fragment.main.setting.tag.ListGroupFragment

class SettingFragment : SjBasicFragment<FragmentSettingBinding>() {
//    private val tagFragment = ListTagFragment()
    private val tagGroupFragment = ListGroupFragment()
    private val domainFragment = ListDomainFragment()
    private val appInfoFragment = AppInfoFragment()

    override fun layoutId(): Int = R.layout.fragment_setting

    override fun onCreateView() {
        binding.settingRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = RecyclerSettingAdapter()
        binding.settingRecyclerView.adapter = adapter
        adapter.setList(getSettingList())
    }

    private fun getSettingList(): List<SettingItemValue> {
        return mutableListOf(
            SettingItemValue("도메인 목록", ::moveToViewDomains),
            SettingItemValue("태그 그룹", ::moveToViewTagGroups),
//            SettingItemValue("태그 목록", ::moveToViewTags),
            SettingItemValue("앱 정보 보기", ::moveToViewData),
        )
    }

    private fun moveToViewDomains() {
        this.moveToOtherFragment(domainFragment)
    }

//    private fun moveToViewTags() {
//        this.moveToOtherFragment(tagFragment)
//    }

    private fun moveToViewTagGroups() {
        this.moveToOtherFragment(tagGroupFragment)
    }

    private fun moveToViewData() {
        this.moveToOtherFragment(appInfoFragment)
    }

}