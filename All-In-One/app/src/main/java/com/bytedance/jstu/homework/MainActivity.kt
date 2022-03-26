package com.bytedance.jstu.homework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton

import com.bytedance.jstu.homework.homework2.ListActivity
import com.bytedance.jstu.homework.homework3.BiliAnim
import com.bytedance.jstu.homework.homework4.ClockActivity
import com.bytedance.jstu.homework.homework5.Translator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addLesson("第二讲 基本用户界面开发", ListActivity::class.java)
        addLesson("第三讲 UI开发进阶", BiliAnim::class.java)
        addLesson("第四讲 复杂应用组件", ClockActivity::class.java)
        addLesson("第五讲 网络", Translator::class.java)
        addLesson("第六讲 存储", Translator::class.java)
        addLesson("第七讲 多媒体基础", Translator::class.java)
        addLesson("第八讲 多媒体进阶", Translator::class.java)
        addLesson("第九讲 新技术趋势", Translator::class.java)
    }

    private fun addLesson(text: String, activityClass: Class<*>) {
        val btn = AppCompatButton(this)
        btn.text = text
        btn.isAllCaps = false
        findViewById<ViewGroup>(R.id.container).addView(btn)
        btn.setOnClickListener {
            startActivity(Intent().apply {
                setClass(this@MainActivity, activityClass)
            })
        }
    }
}