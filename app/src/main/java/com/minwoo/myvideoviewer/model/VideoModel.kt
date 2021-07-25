package com.minwoo.myvideoviewer.model

import android.graphics.Bitmap

data class VideoModel (
    val title: String,
    val subTitle: String,
    val thumbnailImageUrl: Bitmap?,
)