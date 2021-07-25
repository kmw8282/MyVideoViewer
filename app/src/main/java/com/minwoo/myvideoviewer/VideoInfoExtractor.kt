package com.minwoo.myvideoviewer

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.media.ThumbnailUtils
import android.os.Build
import android.provider.MediaStore
import com.minwoo.myvideoviewer.model.VideoModel
import java.lang.Exception

object VideoInfoExtractor {
    lateinit var mediaMetadataRetriever: MediaMetadataRetriever
    /*fun getThumbnailBitmap(videoPath: String): Bitmap? {
        try {
            return ThumbnailUtils.createVideoThumbnail(
                videoPath,
                MediaStore.Video.Thumbnails.FULL_SCREEN_KIND
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }*/
    init {
        mediaMetadataRetriever = MediaMetadataRetriever()
    }

    fun getVideoModel(videoPath: String): VideoModel {
        setMediaMetadataRetriever(videoPath)
        val bitmap = getVideoThumbnailBitmap(videoPath)
        val title = getVideoMetadata()

        mediaMetadataRetriever?.release()

        return VideoModel(title!!, "", bitmap)
    }

    private fun setMediaMetadataRetriever(videoPath: String) {
        if (Build.VERSION.SDK_INT >= 14) mediaMetadataRetriever.setDataSource(videoPath, HashMap<String, String>())
        else mediaMetadataRetriever.setDataSource(videoPath)
    }

    private fun getVideoThumbnailBitmap(videoPath: String): Bitmap? {
        var bitmap: Bitmap? = null

        try {
            bitmap = mediaMetadataRetriever.frameAtTime

        } catch (e: Exception) {

        } finally {
            mediaMetadataRetriever?.release()
        }

        return bitmap
    }

    private fun getVideoMetadata() : String? {
        val title = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
        return title
    }


}