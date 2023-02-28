package com.example.mylink.ui.fragment.main.search.detail_link

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.mylink.R
import com.example.mylink.data.model.LinkDetailValue
import com.example.mylink.databinding.FragmentDetailLinkBinding
import com.example.mylink.ui.activity.EditLinkActivity
import com.example.mylink.ui.component.SjUtil
import com.example.mylink.ui.fragment.basic.SjBasicFragment
import com.example.mylink.viewmodel.detail_link.DetailLinkViewModel


class DetailLinkFragment : SjBasicFragment<FragmentDetailLinkBinding>() {
    private val viewModel: DetailLinkViewModel by activityViewModels()

    override fun layoutId(): Int = R.layout.fragment_detail_link

    override fun onStart() {
        super.onStart()
        viewModel.refreshData()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView() {
        // set variable of binding
        binding.viewModel = viewModel

        // set toolbar menu
        val handlerMap = hashMapOf<Int, () -> Unit>(
            R.id.menu_edit_link to ::startEditActivityToUpdate,
            R.id.menu_delete_link to ::deleteLink
        )
        binding.toolbar.setMenu(R.menu.toolbar_menu_detail_link, handlerMap)

        setOnClickListeners()

    }

    override fun setOnClickListeners() {
        val openListener = View.OnClickListener { startWebBrowser() }
        binding.nameTextView.setOnClickListener(openListener)
        binding.fullUrlTextView.setOnClickListener(openListener)
        binding.previewPreview.setOnClickListener(openListener)
    }

    private fun deleteLink() {
        viewModel.deleteLink()
        popBack()
    }

    private fun startEditActivityToUpdate() {
        val linkData = viewModel.link.value
        if (linkData is LinkDetailValue) {
            val intent = Intent(requireContext(), EditLinkActivity::class.java)
            intent.putExtra("lid", linkData.lid)
            startActivity(intent)
        } else {
            Log.e("cannot start EditActivity", "cause: link data is null")
        }
    }

    private fun startWebBrowser() {
        val url = viewModel.link.value?.fullUrl
        if (url is String) {
            if (SjUtil.checkUrlPrefix(url)) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            } else {
                // string url is wrong
            }
        }
    }


}