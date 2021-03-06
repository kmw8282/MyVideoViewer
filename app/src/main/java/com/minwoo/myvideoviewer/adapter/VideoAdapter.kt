package com.minwoo.myvideoviewer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.minwoo.myvideoviewer.R
import com.minwoo.myvideoviewer.databinding.ItemVideoBinding
import com.minwoo.myvideoviewer.model.VideoModel

class VideoAdapter : ListAdapter<VideoModel, VideoAdapter.ViewHolder>(diffUtil) {
    inner class ViewHolder(private val itemVideoBinding: ItemVideoBinding) : RecyclerView.ViewHolder(itemVideoBinding.root) {
        fun bind(videoModel: VideoModel) {
            itemVideoBinding.videoModel = videoModel

            Glide.with(itemVideoBinding.imgThumbNail.context)
                .load(videoModel.thumbnailImageUrl)
                .into(itemVideoBinding.imgThumbNail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return ViewHolder(ItemVideoBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<VideoModel>() {
            override fun areItemsTheSame(oldItem: VideoModel, newItem: VideoModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: VideoModel, newItem: VideoModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}