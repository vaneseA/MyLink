package com.example.mylink.ui.fragment.main.setting

import android.content.Intent
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylink.R
import com.example.mylink.data.model.SettingItemValue
import com.example.mylink.databinding.FragmentSettingBinding
import com.example.mylink.ui.activity.LockActivity
import com.example.mylink.ui.adapter.recycler.SettingListAdapter
import com.example.mylink.ui.component.BasicDialogFragment
import com.example.mylink.ui.fragment.basic.SjBasicFragment
import com.example.mylink.ui.fragment.main.setting.app_info.AppInfoFragment
import com.example.mylink.ui.fragment.main.setting.domain.ListDomainFragment
import com.example.mylink.ui.fragment.main.setting.personal.PersonalSettingFragment
import com.example.mylink.ui.fragment.main.setting.tag.ListGroupFragment
import com.example.mylink.viewmodel.SettingViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SettingFragment : SjBasicFragment<FragmentSettingBinding>() {
    private val viewModel: SettingViewModel by activityViewModels()

    private val groupFragment = ListGroupFragment()
    private val domainFragment = ListDomainFragment()
    private val appInfoFragment = AppInfoFragment()
    private val personalSettingFragment = PersonalSettingFragment()

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun layoutId(): Int = R.layout.fragment_setting

    override fun onCreateView() {
        binding.settingRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = SettingListAdapter()
        binding.settingRecyclerView.adapter = adapter
        adapter.setList(getSettingList())

        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_SUCCESS) {
                    moveToPersonalSettingWithOutPassword()
                } else {
                    val messageDialog =  BasicDialogFragment("??????","??????????????? ???????????????.",null)
                    messageDialog.show(childFragmentManager,"???????????? ??????")
                }
            }
    }

    private fun getSettingList(): List<SettingItemValue> {
        return mutableListOf(
            SettingItemValue("????????? ??????", ::moveToPersonalSetting),
            SettingItemValue("?????? ?????? ??????", ::moveToViewTagGroups),
            SettingItemValue("????????? ??????", ::moveToViewDomains),
            SettingItemValue("??????????????????", ::moveToViewPlayLists),
            SettingItemValue("??? ?????? ??????", ::moveToViewData),
        )
    }

    private fun moveToPersonalSettingWithOutPassword() {
        moveToOtherFragment(personalSettingFragment)
    }

    private fun moveToPersonalSetting() {
        lifecycleScope.launch(Dispatchers.IO) {
            val password = viewModel.passwordFlow.first()
            if (password.isEmpty()) {
                moveToPersonalSettingWithOutPassword()
            } else {
                val intent = Intent(activity, LockActivity::class.java)
                activityResultLauncher.launch(intent)
            }
        }

    }

    private fun moveToViewDomains() {
        this.moveToOtherFragment(domainFragment)
    }

    private fun moveToViewTagGroups() {
        this.moveToOtherFragment(groupFragment)
    }

    private fun moveToViewData() {
        this.moveToOtherFragment(appInfoFragment)
    }

    private fun moveToViewPlayLists() {
        Toast.makeText(requireContext(), "?????? ?????????????????? ????????? ???????????????.", Toast.LENGTH_LONG).show()
    }
}