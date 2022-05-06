package com.bytedance.sjtu.tiktokdemo.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView


open class BaseHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    var recyclerBaseAdapter: RecyclerView.Adapter<*>? = null
}

