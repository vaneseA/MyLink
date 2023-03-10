package com.example.mylink.ui.fragment.edit_link

import android.view.View
import android.widget.CompoundButton
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.mylink.R
import com.example.mylink.data.model.SjTag
import com.example.mylink.data.model.SjTagGroupWithTags
import com.example.mylink.databinding.FragmentEditLinkBinding
import com.example.mylink.ui.component.EditTagDialogFragment
import com.example.mylink.ui.component.SjTagChip
import com.example.mylink.ui.fragment.basic.SjBasicFragment
import com.example.mylink.viewmodel.edit_link.EditLinkViewModel

class EditLinkFragment : SjBasicFragment<FragmentEditLinkBinding>() {

    val viewModel: EditLinkViewModel by activityViewModels()

    private val dialogFragment = EditTagDialogFragment(::createTag, null)

    private val onCheckListener =
        CompoundButton.OnCheckedChangeListener { btn, isChecked ->
            val chip = btn as SjTagChip
            if (isChecked) {
                viewModel.selectTag(chip.tag)
            } else {
                viewModel.unselectTag(chip.tag)
            }
        }

    override fun layoutId(): Int = R.layout.fragment_edit_link

    override fun onResume() {
        super.onResume()
        viewModel.refreshData()
    }

    override fun onCreateView() {
        // set binding variable
        binding.viewModel = viewModel

        // set toolbar menu
        val handlerMap = hashMapOf<Int, () -> Unit>(R.id.menu_save to ::saveVideo)
        binding.toolbar.setMenu(R.menu.toolbar_menu_edit, handlerMap)

        // show or hide video icon
        viewModel.bindingIsVideo.observe(viewLifecycleOwner
        ) {
            binding.videoTypeIconImageView.visibility =
                if (it) View.VISIBLE
                else View.INVISIBLE
        }

        // set preview image
        viewModel.bindingPreviewImage.observe(viewLifecycleOwner) {
            Glide.with(requireContext())
                .load(it)
                .error(R.drawable.ic_icons8_no_image_100)
                .centerCrop()
                .into(binding.previewImageView)
        }

        // set tagList
        viewModel.tagGroups.observe(viewLifecycleOwner) {
            setCheckableTagsToChipGroupChildren(viewModel.tagDefaultGroup.value, it)
        }
        viewModel.tagDefaultGroup.observe(viewLifecycleOwner) {
            setCheckableTagsToChipGroupChildren(it, viewModel.tagGroups.value)
        }

        setOnClickListeners()

    }

    override fun setOnClickListeners() {
        binding.addTagTextView.setOnClickListener {
            showEditTagDialog()
        }
    }

    private fun showEditTagDialog() {
        dialogFragment.show(childFragmentManager, "?????? ?????? ??? ?????? ????????????")
    }

    private fun createTag(name: String, tag: SjTag?) = viewModel.createTag(name)

    private fun isTagSelected(tag: SjTag) = viewModel.isTagSelected(tag)

    private fun setCheckableTagsToChipGroupChildren(
        defaultGroup: SjTagGroupWithTags?,
        groups: List<SjTagGroupWithTags>?
    ) =
        setTagsToChipGroupChildren(
            defaultGroup,
            groups,
            ::isTagSelected,
            binding.tagChipGroup,
            onCheckListener
        )

    private fun saveVideo() {
        viewModel.saveVideo()
        this.requireActivity().finish()
    }


}