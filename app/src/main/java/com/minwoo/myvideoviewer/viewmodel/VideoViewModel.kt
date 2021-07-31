package com.minwoo.myvideoviewer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.minwoo.myvideoviewer.adapter.VideoAdapter
import com.minwoo.myvideoviewer.model.VideoModel

class VideoViewModel : ViewModel() {

    val videoAdapter: VideoAdapter = VideoAdapter()
    private val liveDataListVideoModel: MutableLiveData<ArrayList<VideoModel>> = MutableLiveData()

    fun setLiveVideoModel(lstVideoModel: ArrayList<VideoModel>) {
        liveDataListVideoModel.value = lstVideoModel
    }

    fun getLiveVieoModel() : MutableLiveData<ArrayList<VideoModel>> {
        return liveDataListVideoModel
    }

    fun getAdapter() : VideoAdapter {
        return this.videoAdapter
    }
}