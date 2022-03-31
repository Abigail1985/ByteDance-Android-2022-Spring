package com.bytedance.jstu.homework.homework6

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDBHelper(val context: Context, name: String, version: Int): SQLiteOpenHelper(context, name, null, version) {

    private val createTodoList = "create table todolist(" +
            "id integer primary key autoincrement," +
            "task text," +
            "status text)"


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createTodoList)
//        db?.execSQL(createMessageList)
        Toast.makeText(context, "create todo db success", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        if (oldVersion <= 1) {
//            db?.execSQL(createMessageList)
//        }
//        if (oldVersion <= 2) {
//            db?.execSQL(createUserList)
//        }
    }
}