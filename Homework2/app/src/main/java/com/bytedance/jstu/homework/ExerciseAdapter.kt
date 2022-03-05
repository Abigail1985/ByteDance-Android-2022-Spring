package com.bytedance.jstu.homework

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ExerciseAdapter(val exerciseList: List<String>) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    private val contentList = mutableListOf<String>()
    private val filteredList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val v = View.inflate(parent.context, R.layout.activity_exercise_adapter, null)
        val viewHolder = ExerciseViewHolder(v)
        viewHolder.itemView.setOnClickListener{
            val position=viewHolder.adapterPosition
            val exercise=exerciseList[position]

            SecondActivity.actionStart(parent.context,exercise)
            Log.d("ddd","传递前的信息是$exercise")
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(filteredList[position])

    }

    override fun getItemCount(): Int = filteredList.size

    fun setContentList(list: List<String>) {
        contentList.clear()
        contentList.addAll(list)
        filteredList.clear()
        filteredList.addAll(list)
        notifyDataSetChanged()
    }

    fun setFilter(keyword: String?) {
        filteredList.clear()
        if (keyword?.isNotEmpty() == true) {
            filteredList.addAll(contentList.filter { it.contains(keyword) })
        } else {
            filteredList.addAll(contentList)
        }
        notifyDataSetChanged()
    }

    class ExerciseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tv = view.findViewById<TextView>(R.id.exercise_tv)

        fun bind(content: String) {
            tv.text = content
        }
    }

}