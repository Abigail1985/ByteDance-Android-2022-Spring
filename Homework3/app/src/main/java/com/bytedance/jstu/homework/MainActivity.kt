package com.bytedance.jstu.homework

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity() {

    private val DURATION = 200L

    private var likeView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var likeView = findViewById<ImageView>(R.id.like_image)
        var coinView = findViewById<ImageView>(R.id.coin_image)
        var starView = findViewById<ImageView>(R.id.star_image)

        likeView.setOnLongClickListener{
            val like_anim_1: Animation = AnimationUtils.loadAnimation(this, R.anim.shake_step_1)
            val like_anim_2: Animation = AnimationUtils.loadAnimation(this, R.anim.shake_step_1)
            val like_anim_3: Animation = AnimationUtils.loadAnimation(this, R.anim.shake_step_1)

            like_anim_1.setDuration(DURATION)
            like_anim_2.setDuration(DURATION)
            like_anim_3.setDuration(DURATION)

            like_anim_1.setAnimationListener(likeView.startAnimation(like_anim_2))
            like_anim_2.setAnimationListener(likeView.startAnimation(like_anim_3))
            like_anim_3.setAnimationListener(likeView.setColorFilter(Color.rgb(23,171,227)))

            val coin_anim_1: Animation = AnimationUtils.loadAnimation(this, R.anim.like_step_1)
            val coin_anim_2: Animation = AnimationUtils.loadAnimation(this, R.anim.like_step_2)
            val coin_anim_3: Animation = AnimationUtils.loadAnimation(this, R.anim.like_step_3)

            coin_anim_1.setDuration(DURATION)
            coin_anim_2.setDuration(DURATION)
            coin_anim_3.setDuration(DURATION)

//            coin_anim_1.setAnimationListener(coinView.startAnimation(coin_anim_2))
//            coin_anim_2.setAnimationListener(coinView.startAnimation(coin_anim_3))
            coin_anim_3.setAnimationListener(coinView.setColorFilter(Color.rgb(23,171,227)))


            val star_anim_1: Animation = AnimationUtils.loadAnimation(this, R.anim.like_step_1)
            val star_anim_2: Animation = AnimationUtils.loadAnimation(this, R.anim.like_step_2)
            val star_anim_3: Animation = AnimationUtils.loadAnimation(this, R.anim.like_step_3)

            star_anim_1.setDuration(DURATION)
            star_anim_2.setDuration(DURATION)
            star_anim_3.setDuration(DURATION)

//            star_anim_1.setAnimationListener(starView.startAnimation(star_anim_2))
//            star_anim_2.setAnimationListener(starView.startAnimation(star_anim_3))
            star_anim_3.setAnimationListener(starView.setColorFilter(Color.rgb(23,171,227)))



//            likeView!!.startAnimation(like_anim_1)
//            coinView!!.startAnimation(coin_anim_1)
//            starView!!.startAnimation(star_anim_1)

            true
        }

        likeView.setOnClickListener {
            val like_anim_1: Animation = AnimationUtils.loadAnimation(this, R.anim.like_step_1)
            val like_anim_2: Animation = AnimationUtils.loadAnimation(this, R.anim.like_step_2)
            val like_anim_3: Animation = AnimationUtils.loadAnimation(this, R.anim.like_step_3)

            like_anim_1.setDuration(DURATION)
            like_anim_2.setDuration(DURATION)
            like_anim_3.setDuration(DURATION)

            like_anim_1.setAnimationListener(likeView.startAnimation(like_anim_2))
            like_anim_2.setAnimationListener(likeView.startAnimation(like_anim_3))
            like_anim_3.setAnimationListener(likeView.setColorFilter(Color.rgb(23,171,227)))
        }

        coinView.setOnClickListener {
            val coin_anim_1: Animation = AnimationUtils.loadAnimation(this, R.anim.like_step_1)
            val coin_anim_2: Animation = AnimationUtils.loadAnimation(this, R.anim.like_step_2)
            val coin_anim_3: Animation = AnimationUtils.loadAnimation(this, R.anim.like_step_3)

            coin_anim_1.setDuration(DURATION)
            coin_anim_2.setDuration(DURATION)
            coin_anim_3.setDuration(DURATION)

            coin_anim_1.setAnimationListener(coinView.startAnimation(coin_anim_2))
            coin_anim_2.setAnimationListener(coinView.startAnimation(coin_anim_3))
            coin_anim_3.setAnimationListener(coinView.setColorFilter(Color.rgb(23,171,227)))
        }

        starView.setOnClickListener {
            val star_anim_1: Animation = AnimationUtils.loadAnimation(this, R.anim.like_step_1)
            val star_anim_2: Animation = AnimationUtils.loadAnimation(this, R.anim.like_step_2)
            val star_anim_3: Animation = AnimationUtils.loadAnimation(this, R.anim.like_step_3)

            star_anim_1.setDuration(DURATION)
            star_anim_2.setDuration(DURATION)
            star_anim_3.setDuration(DURATION)

            star_anim_1.setAnimationListener(starView.startAnimation(star_anim_2))
            star_anim_2.setAnimationListener(starView.startAnimation(star_anim_3))
            star_anim_3.setAnimationListener(starView.setColorFilter(Color.rgb(23,171,227)))
        }
    }
    
}


private fun Animation.setAnimationListener(startAnimation: Unit) {

}



