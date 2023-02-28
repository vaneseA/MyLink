package com.example.mylink.ui.fragment.main.setting.personal

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylink.R
import com.example.mylink.data.model.SettingItemValue
import com.example.mylink.databinding.FragmentPersonalSettingBinding
import com.example.mylink.ui.adapter.recycler.SettingListAdapter
import com.example.mylink.ui.fragment.basic.SjBasicFragment
import com.example.mylink.viewmodel.SettingViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PersonalSettingFragment : SjBasicFragment<FragmentPersonalSettingBinding>() {
    private val viewModel: SettingViewModel by viewModels()
    override fun layoutId(): Int = R.layout.fragment_personal_setting

    override fun onCreateView() {
        binding.settingRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = SettingListAdapter()
        binding.settingRecyclerView.adapter = adapter
        adapter.setList(getSettingList())

        lifecycleScope.launch(Dispatchers.IO) {
            val settingValue = async { viewModel.privateFlow.first() }
            launch(Dispatchers.Main) {
                binding.hidePrivateSwitch.isChecked = settingValue.await()
            }
        }

        binding.hidePrivateSwitch.setOnCheckedChangeListener { compoundButton, isChecked ->
            viewModel.setPrivateMode(isChecked)
        }
    }

    private fun getSettingList(): List<SettingItemValue> {
        return mutableListOf(
            SettingItemValue("비밀번호 설정", ::moveToEditPassword),
        )
    }

    private fun moveToEditPassword() {
        moveToOtherFragment(ChangePasswordFragment())
    }
}