package com.bytedance.jstu.homework.homework7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bytedance.jstu.homework.R

import android.net.Uri
import android.util.Log
import kotlinx.android.synthetic.main.activity_video.*

import android.widget.MediaController
import android.widget.VideoView


class VideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        val mediaController = MediaController(this)
        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")
        videoView.setVideoURI(uri)
        videoView.setMediaController(mediaController)
        play.setOnClickListener {
            if (!videoView.isPlaying) {
                videoView.start() // 开始播放
            }
            Log.d("MainActivity", "video is playing")
        }
        pause.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause() // 暂停播放
            }
        }
        replay.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.resume() // 重新播放
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        videoView.suspend()
    }
}