package com.bytedance.jstu.homework.homework7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import com.bytedance.jstu.homework.R
import com.bytedance.jstu.homework.homework2.ListActivity
import com.bytedance.jstu.homework.homework3.BiliAnim

class MultimediaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multimedia)

        addLesson("Image", ImageActivity::class.java)
        addLesson("Video", VideoActivity::class.java)
    }


    private fun addLesson(text: String, activityClass: Class<*>) {
        val btn = AppCompatButton(this)
        btn.text = text
        btn.isAllCaps = false
        findViewById<ViewGroup>(R.id.container).addView(btn)
        btn.setOnClickListener {
            startActivity(Intent().apply {
                setClass(this@MultimediaActivity, activityClass)
            })
        }
    }
}

