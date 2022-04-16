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


        addImage("https://www.2008php.com/2013_Website_appreciate/2013-12-15/20131215023521Of7AROf7AR.jpg")
        addImage("https://2-im.guokr.com/gkimage/kc/he/w5/kchew5.png")
        addImage("https://pics7.baidu.com/feed/0823dd54564e92586fa3c407c3bac15ecdbf4e06.jpeg?token=c2d11e393766ea2069e39a3e37863f98&s=2A82DC4D46896D57130DC92A03006053")
        addImage("https://t7.baidu.com/it/u=4162611394,4275913936&fm=193&f=GIF")

        val adapter = ViewAdapter()
        adapter.setDatas(pages)
        viewPager.adapter = adapter
    }

    private fun addImage(resId: Int) {
        val imageView =
            layoutInflater.inflate(R.layout.activity_base_multimedia_image_item, null) as ImageView
        Glide.with(this)
            .load(resId)
            .error(R.drawable.error)
            .into(imageView)
        pages.add(imageView)
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