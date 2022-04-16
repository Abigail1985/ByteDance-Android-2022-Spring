package com.bytedance.jstu.homework.homework7

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bytedance.jstu.homework.R
import java.util.ArrayList

class ImageActivity : AppCompatActivity() {
    private val pages: MutableList<View> = ArrayList()
    lateinit var viewPager: ViewPager
    @SuppressLint("SdCardPath")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        viewPager = findViewById(R.id.media_view_pager)

        addImage("https://p9.itc.cn/q_70/images03/20210317/947517063d494dafbaee811ad5c22d6e.gif")
        addImage("https://p0.itc.cn/q_70/images03/20210317/78de632bd4c04b1b9ccc569e8a2b937d.gif")
        addImage("https://2-im.guokr.com/gkimage/kc/he/w5/kchew5.png")
        addImage("https://pics7.baidu.com/feed/0823dd54564e92586fa3c407c3bac15ecdbf4e06.jpeg?token=c2d11e393766ea2069e39a3e37863f98&s=2A82DC4D46896D57130DC92A03006053")


        val adapter = ViewAdapter()
        adapter.setDatas(pages)
        viewPager.adapter = adapter
    }

    private fun addImage(path: String) {
        val imageView =
            layoutInflater.inflate(R.layout.activity_base_multimedia_image_item, null) as ImageView
        Glide.with(this)
            .load(path)
            .apply(RequestOptions().circleCrop().diskCacheStrategy(DiskCacheStrategy.ALL))
            .error(R.drawable.error)
            .into(imageView)
        pages.add(imageView)
    }
}