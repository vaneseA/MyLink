package com.example.mylink.viewmodel.playlist

import android.app.Application
import android.util.SparseArray
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mylink.data.model.VideoData
import com.example.mylink.data.repository.room.SjVideoRepository
import com.example.mylink.ui.component.SjYoutubeExtractListener
import com.example.mylink.ui.component.SjYoutubeExtractor
import com.google.android.exoplayer2.MediaItem

class ListVideoViewModel(application: Application) : AndroidViewModel(application) {
    // setting preview start and end
    private val START_MS: Long = 1000
    private val END_MS: Long = 16000

    var isPrivateMode = false
        set(value){
            field = value
            refreshData()
        }

    // repository
    val videoRepo = SjVideoRepository.getInstance()


    // liveDataList
    private val _playList = MutableLiveData(mutableListOf<MediaItem>())
    val playList: LiveData<MutableList<MediaItem>> get() = _playList
    val videoDatas : LiveData<List<VideoData>> = videoRepo.linksVideo

    init {
        videoDatas.observeForever{
            loadIntoPlayList(it)
        }
    }

    fun refreshData() {
        if (isPrivateMode) {
            videoRepo.postVideosPublic()
        } else {
            videoRepo.postAllVideos()
        }
    }

    private fun loadIntoPlayList(dataList: List<VideoData>) {
        val mediaItems: SparseArray<MediaItem> = SparseArray()
        for (i in dataList.indices) {
            val videoData = dataList[i]
            if (videoData.isYoutubeVideo) {
                val listener = object : SjYoutubeExtractListener {
                    override fun onExtractionComplete(extractedUrl: String) {
                        saveMediaItem(i, extractedUrl, mediaItems, dataList.size)
                    }
                }
                SjYoutubeExtractor(getApplication(), listener).extract(videoData.url)
            } else {
                saveMediaItem(i, videoData.url, mediaItems, dataList.size)
            }
        }
    }

    private fun saveMediaItem(
        position: Int,
        url: String,
        sparseArray: SparseArray<MediaItem>,
        length: Int
    ) {
        sparseArray.append(position, getMediaItemFromUrl(url))
        if (sparseArray.size() == length) {
            val mediaItemList = mutableListOf<MediaItem>()
            for (i in 0 until length) {
                mediaItemList.add(i, sparseArray[i])
            }
            _playList.postValue(mediaItemList)
        }
    }

    private fun getMediaItemFromUrl(url: String): MediaItem {
        return MediaItem.Builder()
            .setUri(url)
            .setClippingConfiguration(
                MediaItem.ClippingConfiguration.Builder()
                    .setStartPositionMs(START_MS)
                    .setEndPositionMs(END_MS)
                    .build()
            ).build()
    }


}