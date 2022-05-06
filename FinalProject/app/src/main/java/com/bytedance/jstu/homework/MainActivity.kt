package com.bytedance.jstu.homework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.bytedance.jstu.homework.adapter.ViewPagerAdapter
import com.bytedance.jstu.homework.entity.VideoInfo
import com.bytedance.jstu.homework.holder.NormalHolder
import com.shuyu.gsyvideoplayer.GSYVideoManager


class MainActivity : AppCompatActivity() {

    private val mList: MutableList<VideoInfo> = ArrayList<VideoInfo>()
    private var viewPagerAdapter: ViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var viewPager2 = findViewById<ViewPager2>(R.id.viewPager)

        setData()

        viewPagerAdapter = ViewPagerAdapter(this, mList)
        viewPager2.orientation = ViewPager2.ORIENTATION_VERTICAL
        viewPager2.adapter = viewPagerAdapter
        viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val playPosition = GSYVideoManager.instance().playPosition
                if (playPosition >= 0) {// 大于0说明有播放

                    if (GSYVideoManager.instance().playTag == NormalHolder.TAG && position != playPosition) {
                        playPosition(position,viewPager2)// 对应的播放列表TAG
                    }
                }
            }
        })
        viewPager2.post { playPosition(0,viewPager2) }

    }


    private fun playPosition(position: Int, viewPager2: ViewPager2) {
        val viewHolder =
            (viewPager2.getChildAt(0) as RecyclerView).findViewHolderForAdapterPosition(position)
        if (viewHolder != null) {
            val recyclerItemNormalHolder: NormalHolder =
                viewHolder as NormalHolder
            recyclerItemNormalHolder.gsyVideoPlayer?.startPlayLogic()
        }
    }

    private fun setData() {
        val data1 = VideoInfo()
        data1.apply {
            textContent="纽约的一个雨天 中国预告片1：终极版 (中文字幕)"
            videoCover="https://img1.doubanio.com/view/photo/l/public/p2559318838.jpg"
            videoUrl="https://vt1.doubanio.com/202205061424/4db5c412c217b0b6bd0711cc4c414716/view/movie/M/402860596.mp4" }
        mList.add(data1)

        val data2 = VideoInfo()
        data2.apply {
            textContent="银翼杀手2049 预告片6：国际版 (中文字幕)"
            videoCover="https://img2.doubanio.com/view/photo/l/public/p2561828133.jpg"
            videoUrl="https://vt1.doubanio.com/202205061427/37dd042de69bddee0d954b7714cac108/view/movie/M/302190293.mp4" }
        mList.add(data2)

        val data3 = VideoInfo()
        data3.apply {
            textContent="星际穿越 中国预告片1：定档版 (中文字幕)"
            videoCover="https://img3.doubanio.com/view/photo/l/public/p2208890140.jpg"
            videoUrl="https://vt1.doubanio.com/202205061428/e7acf132d287ef996ed68d79d555db8d/view/movie/M/402630852.mp4" }
        mList.add(data3)

        val data4 = VideoInfo()
        data4.apply {
            textContent="人生切割术 第一季 预告片"
            videoCover="https://img9.doubanio.com/view/photo/l/public/p2870467165.jpg"
            videoUrl="https://vt1.doubanio.com/202205061429/fa8394aff37d77e1fef436688e532851/view/movie/M/402850611.mp4" }
        mList.add(data4)

        val data5 = VideoInfo()
        data4.apply {
            textContent="怪奇物语 第四季 预告片1 (中文字幕)"
            videoCover="https://img2.doubanio.com/view/photo/l/public/p2870274481.jpg"
            videoUrl="https://vt1.doubanio.com/202205061430/5e0787fc0a1e9d022a1d39ca7f76bcdf/view/movie/M/402880322.mp4" }
        mList.add(data5)
    }
}