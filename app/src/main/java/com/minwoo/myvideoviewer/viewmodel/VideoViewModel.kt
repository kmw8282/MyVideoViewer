package com.minwoo.myvideoviewer.viewmodel

import androidx.lifecycle.ViewModel
import com.minwoo.myvideoviewer.adapter.VideoAdapter

class VideoViewModel : ViewModel() {

    private lateinit var videoAdapter: VideoAdapter

    fun getVideoAdapter() : VideoAdapter {
        return this.videoAdapter
    }

    fun setVideoAdapter(videoAdapter: VideoAdapter) : Unit {
        this.videoAdapter = videoAdapter
    }


}