package com.bytedance.jstu.homework.homework6

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.jstu.homework.R


class TodoAdapter(val todoList: List<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var contentList = mutableListOf<Todo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val v = View.inflate(parent.context, R.layout.activity_todo_adapter, null)
        val viewHolder = TodoViewHolder(v)
        return viewHolder
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = contentList[position]
        holder.todotext.text = todo.task
        holder.tododone.isChecked = todo.status != "do"

    }

    override fun getItemCount(): Int = todoList.size

    fun setContentList(list: List<Todo>) {
        contentList.clear()
        contentList.addAll(list)
        notifyDataSetChanged()
    }


    class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val todotext = view.findViewById<TextView>(R.id.todo_textview)
        val tododone = view.findViewById<CheckBox>(R.id.todo_done_button)
    }

}