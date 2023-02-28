package com.example.mylink.ui.fragment.lock

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.mylink.R
import com.example.mylink.databinding.FragmentLockBinding
import com.example.mylink.ui.fragment.basic.SjBasicFragment
import com.example.mylink.viewmodel.lock.LockViewModel

class LockFragment : SjBasicFragment<FragmentLockBinding>() {
    private val viewModel: LockViewModel by activityViewModels()

    override fun layoutId(): Int = R.layout.fragment_lock

    override fun onCreateView() {
        binding.viewModel = viewModel
    }
}