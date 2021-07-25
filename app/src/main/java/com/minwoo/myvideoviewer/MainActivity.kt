package com.minwoo.myvideoviewer

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import androidx.core.content.ContextCompat
import com.minwoo.myvideoviewer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initAdapter()
        initRecyclerView()

        fetchVideoInfo()
    }

    private fun getAllVideoPath() : Array<String> {
        val resultPath = arrayListOf<String>()

        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("mp4")
            val selectionArgsVideo
        }

        return arrayOf()
    }

    private fun fetchVideoInfo() {
        // 비디오 경로들을 얻어온다.
        val cursor: Cursor? = null

        cursor.use { cursor ->
            val array = arrayListOf<String>(MediaStore.Video.Media.DATA)
            cursor = contentResolver.query()
        }






        VideoInfoExtractor.getVideoModel()
    }

    private fun initAdapter(){
        videoAdapter = VideoAdapter()
    }

    private fun initRecyclerView() {

        activityMainBinding.videoRecyclerView.apply {
            adapter = videoAdapter
            layoutManager = null
        }


    }
}