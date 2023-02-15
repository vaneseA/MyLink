package com.example.mylink.ui.activity

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.mylink.R
import com.example.mylink.databinding.ActivityMainBinding
import com.example.mylink.ui.activity.basic.ViewBindingBasicActivity
import com.example.mylink.ui.fragment.ViewDomainFragment
import com.example.mylink.ui.fragment.ViewLinkFragment
import com.example.mylink.ui.fragment.ViewTagFragment
import com.example.mylink.viewmodel.ReadLinkViewModel


class MainActivity : ViewBindingBasicActivity<ActivityMainBinding>() {
    val viewModel: ReadLinkViewModel by viewModels()

    // 바텀 내비에 따라 부착할 fragment들.
    private val linkFragment = ViewLinkFragment()
    private val tagFragment = ViewTagFragment()
    private val domainFragment = ViewDomainFragment()

    override fun viewBindingInflate(inflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(inflater)

    override fun homeFragment(): Fragment{
        // 최초 부착할 프래그먼트와 바텀내비의 선택된 메뉴를 동기화.
        binding.bottomNavigation.selectedItemId=R.id.linkItem
        return linkFragment
    }

    override fun onCreate() {
        // 바텀 내비 메뉴 선택시 프래그먼트 변경.
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.linkItem -> {
                    this.replaceFragmentTo(linkFragment)
                    true
                }
                R.id.domainItem -> {
                    this.replaceFragmentTo(domainFragment)
                    true
                }
                R.id.tagItem -> {
                    this.replaceFragmentTo(tagFragment)
                    true
                }
                R.id.settingItem -> true
                else -> false
            }
        }
    }

}