package com.bytedance.jstu.homework.homework2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.bytedance.jstu.homework.R
import java.awt.font.TextAttribute

class DetailActivity : AppCompatActivity() {
//    private val EXERCISE_NAME="exercise_name"

    companion object {
        fun actionStart(context: Context,title: String) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("exercise_title",title)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val title = intent.getStringExtra("exercise_title")
        Log.d("ddd","传递后的title是$title")
        val exerciseContentText=findViewById<TextView>(R.id.exerciseContentText)
        exerciseContentText.text=generateContent(title)
    }

    private fun generateContent(title:String?)=title?.repeat(10)
}