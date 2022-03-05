package com.bytedance.jstu.homework

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val exerciseList=ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.recycler_view)//rv=recyclerview
        rv.layoutManager = LinearLayoutManager(this)

        val adapter = ExerciseAdapter(exerciseList)
        initExercises()
//        val data = (1..100).map { "这是第${it}行" }
        adapter.setContentList(exerciseList)
        rv.adapter = adapter

        val et = findViewById<EditText>(R.id.words_et)//et=edittext
        et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                adapter.setFilter(p0.toString())
            }
        })
    }

    private fun initExercises(){
        repeat(10){
            exerciseList.add("两数之和")
            exerciseList.add("两数相加")
            exerciseList.add("无重复字符的最长子串")
        }
    }
}