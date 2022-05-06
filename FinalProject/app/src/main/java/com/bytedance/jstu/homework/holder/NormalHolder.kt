package com.bytedance.jstu.homework.holder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bytedance.jstu.homework.R
import com.bytedance.jstu.homework.entity.VideoInfo
import com.bytedance.jstu.homework.video.SampleCoverVideo
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer


open class NormalHolder(context: Context?, v: View?) : BaseHolder(v) {
    private var context: Context? = null

    var gsyVideoPlayer: SampleCoverVideo? = null
    var tvContent: TextView? = null
    var imageView: ImageView
    var gsyVideoOptionBuilder: GSYVideoOptionBuilder

    fun onBind(position: Int, videoModel: VideoInfo) {
        val videoUrl: String? = videoModel.videoUrl
        val textContent: String? = videoModel.textContent
        context?.let { Glide.with(it).load(videoModel.videoCover).into(imageView) }
        tvContent!!.text = " $textContent"
        val header: MutableMap<String, String> = HashMap()
        header["ee"] = "33"

        gsyVideoOptionBuilder
            .setIsTouchWiget(false)
            .setThumbImageView(imageView)
            .setUrl(videoUrl)
            .setVideoTitle(textContent)
            .setCacheWithPlay(true)
            .setRotateViewAuto(true)
            .setLockLand(true)
            .setPlayTag(TAG)
            .setMapHeadData(header)
            .setShowFullAnimation(true)
            .setNeedLockFull(true)
            .setPlayPosition(position)
            .setVideoAllCallBack(object : GSYSampleCallBack() {
                override fun onPrepared(url: String?, vararg objects: Any?) {
                    super.onPrepared(url, objects)
                    GSYVideoManager.instance().isNeedMute = false
                }

                override fun onQuitFullscreen(url: String?, vararg objects: Any?) {
                    super.onQuitFullscreen(url, objects)
                    GSYVideoManager.instance().isNeedMute = false
                }

                override fun onEnterFullscreen(url: String?, vararg objects: Any?) {
                    super.onEnterFullscreen(url, objects)
                    GSYVideoManager.instance().isNeedMute = false
                    gsyVideoPlayer?.currentPlayer?.titleTextView?.text = objects[0] as String?
                }
            }).build(gsyVideoPlayer)


        //增加title
        gsyVideoPlayer?.titleTextView?.visibility = View.GONE

        //设置返回键
        gsyVideoPlayer?.backButton?.visibility = View.GONE

        //设置全屏按键功能
        gsyVideoPlayer?.fullscreenButton
            ?.setOnClickListener(View.OnClickListener { resolveFullBtn(gsyVideoPlayer) })

    }


    private fun resolveFullBtn(standardGSYVideoPlayer: StandardGSYVideoPlayer?) {
        standardGSYVideoPlayer!!.startWindowFullscreen(context, true, true)
    }

    val player: SampleCoverVideo?
        get() = gsyVideoPlayer

    companion object {
        const val TAG = "NormalHolder"
    }

    init {
        this.context = context
        if (v != null) {
            gsyVideoPlayer=v.findViewById(R.id.video_item_player)
            tvContent=v.findViewById(R.id.tv_content)
        }
        imageView = ImageView(context)
        gsyVideoOptionBuilder = GSYVideoOptionBuilder()
    }
}