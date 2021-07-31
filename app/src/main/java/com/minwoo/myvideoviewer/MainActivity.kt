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
import androidx.recyclerview.widget.LinearLayoutManager
import com.minwoo.myvideoviewer.adapter.VideoAdapter
import com.minwoo.myvideoviewer.databinding.ActivityMainBinding
import com.minwoo.myvideoviewer.model.VideoModel
import com.minwoo.myvideoviewer.viewmodel.VideoViewModel

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var videoViewModel: VideoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        this.videoViewModel = VideoViewModel()
        activityMainBinding.videoViewModel = this.videoViewModel

        // 데이터를 만든다.
        val arrayList = arrayListOf<VideoModel>()
        arrayList.add(VideoModel("하이","테스트", null))

        this.videoViewModel.getLiveVieoModel().observe(this) {
            this.videoViewModel.getAdapter().submitList(arrayList) {

            }
        }

        this.videoViewModel.setLiveVideoModel(arrayList)


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
}