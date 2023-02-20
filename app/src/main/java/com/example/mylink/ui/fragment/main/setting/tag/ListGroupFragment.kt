package com.example.mylink.ui.fragment.main.setting.tag

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylink.R
import com.example.mylink.data.model.SjTagGroup
import com.example.mylink.databinding.FragmentListTagGroupBinding
import com.example.mylink.ui.adapter.recycler.TagGroupListAdapter
import com.example.mylink.ui.component.EditTagGroupDialogFragment
import com.example.mylink.ui.fragment.basic.SjBasicFragment
import com.example.mylink.viewmodel.tag.ListGroupViewModel


class ListGroupFragment : SjBasicFragment<FragmentListTagGroupBinding>() {
    val viewModel: ListGroupViewModel by activityViewModels()

    // override methods
    override fun layoutId(): Int = R.layout.fragment_list_tag_group

    override fun onCreateView() {
        // set binding variable
        binding.viewModel = viewModel

        // set toolbar menu
        val handlerMap = hashMapOf<Int, () -> Unit>(
            R.id.menu_add_group to ::showEditTagGroupDialog
        )
        binding.toolbar.setMenu(R.menu.toolbar_menu_tag, handlerMap = handlerMap)

        // set recyclerView
        binding.tagGroupRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = TagGroupListAdapter(
            deleteOperation = ::deleteGroupByGid,
            editOperation = ::moveToEditTagGroupFragment,
            renameOperation = ::showEditTagGroupDialogToEdit,
        )
        binding.tagGroupRecyclerView.adapter = adapter
        viewModel.tagGroups.observe(viewLifecycleOwner, {
            adapter.setList(it)
        })
    }

    // handle menu event
    private fun showEditTagGroupDialog() {
        val dialogFragment = EditTagGroupDialogFragment(::createTagGroup, null)
        dialogFragment.show(childFragmentManager, "새 태그 그룹 생성하기")
    }

    private fun createTagGroup(name: String, isPrivate: Boolean, group: SjTagGroup? = null) =
        viewModel.editTagGroup(name, isPrivate, group)


    // handle recycler event
    private fun moveToEditTagGroupFragment(gid: Int) {
        moveToOtherFragment(EditTagInGroupFragment.newInstance(gid))
    }

    private fun showEditTagGroupDialogToEdit(tagGroup: SjTagGroup) {
        val dialogFragment = EditTagGroupDialogFragment(::createTagGroup, tagGroup)
        dialogFragment.show(childFragmentManager, "새 태그 그룹 생성하기")
    }

    private fun deleteGroupByGid(gid: Int) {
        viewModel.deleteTagGroup(gid)
    }


}