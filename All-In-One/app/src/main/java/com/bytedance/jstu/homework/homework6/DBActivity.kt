package com.bytedance.jstu.homework.homework6

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.database.getStringOrNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.jstu.homework.R
import java.lang.NullPointerException
import java.lang.StringBuilder

class DBActivity: AppCompatActivity() {

    private val add: Button by lazy {
        findViewById(R.id.add)
    }
    private val deleteall: Button by lazy {
        findViewById(R.id.delete_all)
    }
    private val query: Button by lazy {
        findViewById(R.id.query)
    }


    private val dbHelper = MyDBHelper(this, "Todo.db", 1)
    private var db: SQLiteDatabase? = null
    private var edittodo: EditText? = null
    private var todoList = mutableListOf<Todo>()

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db_layout)

        edittodo = findViewById<EditText>(R.id.edit_todo)
        db = dbHelper.writableDatabase

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager=LinearLayoutManager(this)
        layoutManager.orientation=LinearLayoutManager.VERTICAL
        recyclerView.layoutManager=layoutManager
        val adapter = TodoAdapter(todoList)

        updateList()
        adapter.setContentList(todoList)
        recyclerView.adapter=adapter

        deleteall.setOnClickListener {
            db?.execSQL("delete from todolist")
            updateList()
            adapter.setContentList(todoList)
        }

        add.setOnClickListener {

            val values = ContentValues().apply {
                put("task", "${edittodo?.text}")
                put("status", "do")//新加入的都带上未完成标签
            }
            db?.insert("todolist", null, values)
            updateList()
            adapter.setContentList(todoList)

        }

        query.setOnClickListener {
            updateList()
            db?.delete("todolist", "status = ?", arrayOf("done"))
            updateList()
            adapter.setContentList(todoList)
        }
    }
    fun deleteTask(view: View){
        val parent = view.parent as View
        val taskTextView = parent.findViewById<TextView>(R.id.todo_textview)
        val task = taskTextView.text.toString()


        val values = ContentValues().apply {
            put("status", "done")
        }
        db?.update("todolist",values,"task = ?",arrayOf(task))
        updateList()
    }

    private fun updateList() {
        todoList.clear()
        val cursor = (db?: dbHelper.writableDatabase).query("todolist", null, null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                val task = cursor.getString(cursor.getColumnIndexOrThrow("task"))
                val status = cursor.getString(cursor.getColumnIndexOrThrow("status"))
                todoList.add(Todo(task, status))
            } while (cursor.moveToNext())
        }
        cursor.close()
    }
}