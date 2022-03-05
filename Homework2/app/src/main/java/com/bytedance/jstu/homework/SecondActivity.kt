package com.bytedance.jstu.homework

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import java.awt.font.TextAttribute

class SecondActivity : AppCompatActivity() {
//    private val EXERCISE_NAME="exercise_name"

    companion object {
        fun actionStart(context: Context,title: String) {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("exercise_title",title)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val title = intent.getStringExtra("exercise_title")
        Log.d("ddd","传递后的title是$title")
        val exerciseContentText=findViewById<TextView>(R.id.exerciseContentText)
        exerciseContentText.text=generateContent(title)
    }

    private fun generateContent(title:String?)=title?.repeat(10)
}