package com.minwoo.myvideoviewer

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.minwoo.myvideoviewer.adapter.VideoAdapter
import com.minwoo.myvideoviewer.databinding.ActivityMainBinding
import com.minwoo.myvideoviewer.viewmodel.VideoViewModel

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(activityMainBinding.root)

        activityMainBinding.videoVideModel = VideoViewModel()

        if (requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            getAllVideoPath()
        } else {
            Toast.makeText(applicationContext, "권한이 없습니다. 프로그램을 종료합니다.", Toast.LENGTH_SHORT).show()
        }

        // initAdapter()
        // initRecyclerView()
        // fetchVideoInfo()
    }

    private fun getAllVideoPath(): ArrayList<String> {
        val resultPath = arrayListOf<String>()

        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val selectionMimeType = MediaStore.Files.FileColumns.MIME_TYPE + "=?"
            val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("mp4")
            val selectionArgsMp4 = arrayOf(mimeType)

            val cursor = contentResolver.query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                arrayOf(MediaStore.Video.Media.DATA),
                selectionMimeType,
                selectionArgsMp4,
                null
            )

            if (cursor?.count == 0) {
                return resultPath
            }


            cursor?.let {
                while (it.moveToNext()) {
                    resultPath.add(it.getString(cursor.getColumnIndexOrThrow(android.provider.MediaStore.Video.Media.DATA)))
                }
            }
        } else {
            //requestPermission()
        }

        Log.d("22",resultPath.toString())

        return resultPath
    }

    private fun requestPermission(permission: String): Boolean {
        val permissionState = ContextCompat.checkSelfPermission(
            applicationContext,
            permission
        )

        if (permissionState != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(permission),
                1000
            )
        } else {
            return true
        }
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getAllVideoPath()
            }
        }
    }

    private fun fetchVideoInfo() {
        /*// 비디오 경로들을 얻어온다.
        val cursor: Cursor? = null

        cursor.use { cursor ->
            val array = arrayListOf<String>(MediaStore.Video.Media.DATA)
            cursor = contentResolver.query()
        }
        VideoInfoExtractor.getVideoModel()*/
    }

    private fun initAdapter() {
        videoAdapter = VideoAdapter()
    }

    private fun initRecyclerView() {

        activityMainBinding.videoRecyclerView.apply {
            adapter = videoAdapter
            layoutManager = null
        }


    }
}