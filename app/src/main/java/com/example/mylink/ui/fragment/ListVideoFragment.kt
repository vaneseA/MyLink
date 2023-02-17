package com.example.mylink.ui.fragment

import android.annotation.SuppressLint
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mylink.R
import com.example.mylink.data.model.SjTag
import com.example.mylink.databinding.FragmentListVideoBinding
import com.example.mylink.ui.adapter.RecyclerVideoAdapter
import com.example.mylink.ui.adapter.VideoRecyclerViewHolder
import com.example.mylink.ui.fragment.basic.SjBasicFragment
import com.example.mylink.viewmodel.ListVideoViewModel
import com.github.yeeun_yun97.toy.linksaver.ui.fragment.DetailVideoFragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player

class ListVideoFragment : SjBasicFragment<FragmentListVideoBinding>() {

    private val viewModel: ListVideoViewModel by activityViewModels()

    private var _player: ExoPlayer? = null
    private val player: ExoPlayer get() = _player!!

    data class VideoData(
        val url: String,
        val name: String,
        val thumbnail: String,
        val tagList: List<SjTag>
    )

    override fun layoutId(): Int = R.layout.fragment_list_video

    override fun onCreateView() {

        _player = ExoPlayer.Builder(requireContext()).build()
        player.repeatMode = Player.REPEAT_MODE_ONE

        val manager = LinearLayoutManager(context)
        binding.videoRecyclerView.layoutManager = manager
        val adapter = RecyclerVideoAdapter(player, ::moveToDetailFragment)
        binding.videoRecyclerView.adapter = adapter

        adapter.setList(viewModel.getDataList())

        binding.videoRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            @SuppressLint("StaticFieldLeak")
            private var prevViewHolder: VideoRecyclerViewHolder? = null

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val index = manager.findFirstCompletelyVisibleItemPosition()
                val currentViewHolder =
                    binding.videoRecyclerView.findViewHolderForLayoutPosition(index) as VideoRecyclerViewHolder
                prevViewHolder?.playStop()
                currentViewHolder.playStart()
                prevViewHolder = currentViewHolder
            }
        })

    }

    override fun onStop() {
        super.onStop()

        // release player
        player.release()
        _player = null
    }


    private fun moveToDetailFragment() {
        moveToOtherFragment(DetailVideoFragment.newInstance(0))
    }


}