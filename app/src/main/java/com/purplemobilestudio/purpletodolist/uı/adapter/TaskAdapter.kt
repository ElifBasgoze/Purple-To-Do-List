package com.purplemobilestudio.purpletodolist.uı.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.purplemobilestudio.purpletodolist.Model.Task
import com.purplemobilestudio.purpletodolist.R

class TaskAdapter(var context:Context,var taskList: ArrayList<Task>): RecyclerView.Adapter<TaskAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int= taskList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.taskTitle.text=taskList[position].title
        holder.taskContent.text=taskList[position].content
        holder.taskdeadline.text=taskList[position].deadline
    }

    fun UpdateList(newList: ArrayList<Task>)
    {
        taskList.clear()
        taskList.addAll(newList)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) :RecyclerView.ViewHolder(view)
    {
        val taskTitle=view.findViewById<TextView>(R.id.title_tv)
        val taskContent=view.findViewById<TextView>(R.id.content_tv)
        val taskdeadline=view.findViewById<TextView>(R.id.deadline_tv)
    }

}