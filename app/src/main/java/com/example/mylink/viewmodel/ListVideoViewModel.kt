package com.example.mylink.viewmodel

import com.example.mylink.data.model.SjTag
import com.example.mylink.ui.fragment.ListVideoFragment
import com.example.mylink.viewmodel.basic.BasicViewModelWithRepository


class ListVideoViewModel : BasicViewModelWithRepository() {

    fun getDataList(): List<ListVideoFragment.VideoData> {
        val list = listOf(
            ListVideoFragment.VideoData(
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                "Big Buck Bunny",

                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/BigBuckBunny.jpg",
                listOf(
                    SjTag(name = "Bunny"),
                    SjTag(name = "Big"),
                    SjTag(name = "Blender")
                )
            ),

            ListVideoFragment.VideoData(
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
                "For Bigger Blazes",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerBlazes.jpg",
                listOf(
                    SjTag(name = "Blaze"),
                    SjTag(name = "google")
                )
            ),

            ListVideoFragment.VideoData(
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                "Elephant Dream",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ElephantsDream.jpg",
                listOf(
                    SjTag(name = "Elephant"),
                    SjTag(name = "Blender")
                )
            ),

            ListVideoFragment.VideoData(
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4",
                "What care can you get for a grand?",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/WhatCarCanYouGetForAGrand.jpg",
                listOf(
                    SjTag(name = "Blend"),
                    SjTag(name = "Garage419")
                )
            ),


            ListVideoFragment.VideoData(
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
                "For Bigger Escape",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerEscapes.jpg",
                listOf(
                    SjTag(name = "Escape"),
                    SjTag(name = "Google")
                )
            )
        )
        return list
    }
}