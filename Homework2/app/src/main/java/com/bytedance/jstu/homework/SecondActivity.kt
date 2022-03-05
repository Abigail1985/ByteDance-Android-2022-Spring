package com.bytedance.jstu.homework

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SecondActivity : AppCompatActivity() {
//    private val EXERCISE_NAME="exercise_name"

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, SecondActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


//        val title = intent.getStringExtra("exercise_title")
//        exerciseContentText.text=title

    }
}