package com.bytedance.jstu.homework.entity

import java.io.Serializable


class VideoInfo : Serializable {
    // 视频播放Url
    var videoUrl: String? = null

    // 文本内容
    var textContent: String? = null

    // 视频封面
    var videoCover: String? = null

    companion object {
        const val serialVersionUID = 1L
    }
}