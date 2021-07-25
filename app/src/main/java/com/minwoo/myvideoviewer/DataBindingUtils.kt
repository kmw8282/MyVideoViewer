package com.minwoo.myvideoviewer

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object DataBindingUtils {
    @JvmStatic
    @BindingAdapter("setAdapter")
    fun setAdapter(recyclerView: RecyclerView, recyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        recyclerView.adapter = recyclerViewAdapter
    }
}