package com.vermaji.asgnapp.ui.mainScreen.task

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.Task
import com.vermaji.asgnapp.R
import com.vermaji.asgnapp.ui.mainScreen.databaseUtils.TaskModel

class TaskAdapter(val context:Context,private val list: List<TaskModel>): RecyclerView.Adapter<TaskViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.task_card_view,parent,false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class TaskViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
    fun bind(taskModel: TaskModel){
        itemView.findViewById<TextView>(R.id.idTaskTitle).text = taskModel.title
        if (taskModel.id!! <5){
            itemView.findViewById<ImageView>(R.id.idCheckImage).setImageResource(R.drawable.ic_check_true_icon)
        }
    }
}
