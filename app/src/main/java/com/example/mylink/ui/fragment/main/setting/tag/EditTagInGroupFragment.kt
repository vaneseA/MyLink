package com.example.mylink.ui.fragment.main.setting.tag

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.mylink.R
import com.example.mylink.data.model.SjTag
import com.example.mylink.databinding.FragmentListTagBinding
import com.example.mylink.ui.component.EditTagDialogFragment
import com.example.mylink.ui.component.SjTagChip
import com.example.mylink.ui.component.TagValue
import com.example.mylink.ui.fragment.basic.SjBasicFragment
import com.example.mylink.viewmodel.tag.TagGroupEditViewModel

class EditTagInGroupFragment : SjBasicFragment<FragmentListTagBinding>() {
    val viewModel: TagGroupEditViewModel by activityViewModels()

    // override methods
    override fun layoutId(): Int = R.layout.fragment_list_tag

    override fun onResume() {
        super.onResume()
        viewModel.loadTagGroup()
    }

    override fun onCreateView() {
        binding.viewModel = viewModel

        val handlerMap = hashMapOf<Int, () -> Unit>(
            R.id.menu_group_new_tag to ::createNewTag,
            R.id.menu_group_swap to ::moveToSwapTagGroupFragment,
        )
        binding.toolbar.setMenu(R.menu.toolbar_menu_tag_group, handlerMap)

        viewModel.tagGroup.observe(viewLifecycleOwner) {
            binding.toolbar.toolbarTitle = it.tagGroup.name
            addTagChipsToTagChipGroup(it.tags)
        }
    }

    private fun createNewTag() {
        val tagGroupWithTag = viewModel.tagGroup.value
        val dialogFragment = EditTagDialogFragment(::editTag, tagGroupWithTag?.tagGroup)
        dialogFragment.show(childFragmentManager, "새 태그 만들기")
    }

    // handle user click event
    private fun renameTag(tag: SjTag) {
        val tagGroupWithTag = viewModel.tagGroup.value
        val dialogFragment = EditTagDialogFragment(::editTag, tagGroupWithTag?.tagGroup, tag)
        dialogFragment.show(childFragmentManager, "태그 이름 수정하기")
    }

    private fun editTag(name: String, tag: SjTag?) {
        viewModel.editTag(tag, name)
        viewModel.loadTagGroup()
    }

    private fun moveToSwapTagGroupFragment() {
        moveToOtherFragment(SwapTagGroupFragment.newInstance(viewModel.gid))
    }

    // add chipGroup chips
    private fun addTagChipsToTagChipGroup(it: List<SjTag>) {
        binding.tagChipGroup.removeAllViews()
        for (tag in it) {
            val chip = SjTagChip(requireContext())
            chip.setTagValue(TagValue(tag))
            chip.setDeletableAndLongClickableMode(
                deleteOperation = ::deleteTag,
                longClickOperation = ::renameTag
            )
            binding.tagChipGroup.addView(chip)
        }
    }

    private fun deleteTag(tag: SjTag) {
        viewModel.deleteTag(tag)
        //TODO
        //여기서 확인 다이얼로그 표시하면 좋을 것 같음.
        //링크와의 연결도 사라진다고 알리기.
    }


}


