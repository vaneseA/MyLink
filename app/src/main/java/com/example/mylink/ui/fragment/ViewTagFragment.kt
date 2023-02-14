package com.example.mylink.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.mylink.R
import com.example.mylink.data.model.SjTag
import com.example.mylink.databinding.FragmentViewTagBinding
import com.example.mylink.ui.component.SjTagChip
import com.example.mylink.ui.fragment.basic.DataBindingBasicFragment
import com.example.mylink.viewmodel.TagViewModel

class ViewTagFragment : DataBindingBasicFragment<FragmentViewTagBinding>() {
    val viewModel: TagViewModel by viewModels()

    override fun layoutId(): Int = R.layout.fragment_view_tag

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.tags.observe(
            viewLifecycleOwner, {
                binding.tagChipGroup.removeAllViews()
                for (tag in it) {
                    val chip = SjTagChip(requireContext(), tag)
                    chip.isCheckable = false
                    chip.isCloseIconVisible = true
                    chip.isClickable = false
                    chip.setOnCloseIconClickListener {
                        //여기서 확인 다이얼로그 표시하면 좋을 것 같음.
                        //링크와의 연결도 사라진다고 알리기.
                        viewModel.deleteTag(tag)
                    }
                    chip.setOnLongClickListener {
                        moveToEditTagFragment(tag)
                        true
                    }
                    binding.tagChipGroup.addView(chip)
                }
            }
        )

    }

    private fun moveToEditTagFragment(tag: SjTag){
        moveToOtherFragment(EditTagFragment.newInstance(tag))
    }
}