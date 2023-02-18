package com.example.mylink.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.mylink.R
import com.example.mylink.data.model.SjLink
import com.example.mylink.data.model.SjTag
import com.example.mylink.databinding.FragmentDetailVideoBinding
import com.example.mylink.ui.activity.EditLinkActivity
import com.example.mylink.ui.fragment.PlayVideoFragment
import com.example.mylink.ui.fragment.basic.SjBasicFragment
import com.example.mylink.viewmodel.DetailLinkViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class DetailVideoFragment : SjBasicFragment<FragmentDetailVideoBinding>() {
    private val viewModel: DetailLinkViewModel by activityViewModels()
    private var lid: Int = -1

    private var _player: ExoPlayer? = null
    private val player: ExoPlayer get() = _player!!

    companion object {
        fun newInstance(lid: Int): DetailVideoFragment {
            val fragment = DetailVideoFragment()
            fragment.arguments = Bundle().apply {
                putInt("lid", lid)
            }
            return fragment
        }
    }

    override fun layoutId(): Int = R.layout.fragment_detail_video

    override fun onCreateView() {
        // get lid from argument
        lid = requireArguments().getInt("lid", -1)
        if (lid > 0) {
            viewModel.loadLinkData(lid)
        }

        viewModel.link.observe(viewLifecycleOwner,{
            val fullUrl = it.domain.url+ it.link.url
            val mediaItem = MediaItem.fromUri(fullUrl)
            player.setMediaItem(mediaItem)
            player.prepare()
        })

        // set variable of binding
        binding.viewModel = viewModel

    }

    override fun onStart() {
        super.onStart()

        /* Exoplayer docs clone */
        // create player
        _player = ExoPlayer.Builder(requireContext()).build()

        // connect player to view
        binding.playerView.player = player

    }

    override fun onResume() {
        super.onResume()
        player.play()
    }

    override fun onStop() {
        super.onStop()

        // release player
        player.release()
        _player = null

        // clear viewModel data when screen not visible
        viewModel.clearData()
    }


    private fun moveToPlayFragment() {
        val url = "https://www.youtube.com/watch?v=H0M1yU6uO30"
        moveToOtherFragment(PlayVideoFragment.newInstance(url))
        Toast.makeText(context, "pressed", Toast.LENGTH_LONG).show()
    }


}