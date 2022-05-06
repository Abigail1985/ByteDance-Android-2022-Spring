package com.bytedance.jstu.homework.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.jstu.homework.R
import com.bytedance.jstu.homework.entity.VideoInfo
import com.bytedance.jstu.homework.holder.NormalHolder


class ViewPagerAdapter(context: Context?, itemDataList: List<VideoInfo>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var itemDataList: List<VideoInfo>? = null
    private var context: Context? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val v: View = LayoutInflater.from(context)
            .inflate(R.layout.layout_viewpager2_item, parent, false)
        return NormalHolder(context, v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val recyclerItemViewHolder: NormalHolder = holder as NormalHolder
        recyclerItemViewHolder.recyclerBaseAdapter=this
        recyclerItemViewHolder.onBind(position, itemDataList!![position])
    }

    override fun getItemCount(): Int {
        return itemDataList!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return 1
    }

    companion object {
        private const val TAG = "ViewPagerAdapter"
    }

    init {
        this.itemDataList = itemDataList
        this.context = context
    }
}
