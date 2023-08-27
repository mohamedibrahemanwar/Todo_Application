package com.example.todo.ui.home.tabs.tasks_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.todo.databases.Task
import com.example.todoapp.databinding.ItemRecyclerBinding

class TasksAdapter(var tasks: List<Task>?) : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    fun interface OnItemClickListnerCard {
        fun onClick(task: Task)
    }

    var onItemClickListnerCard: OnItemClickListnerCard? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks!![position])
        if (onItemClickListnerCard != null) {
            holder.itemBinding.card.setOnLongClickListener {
                onItemClickListnerCard?.onClick(tasks!![position])
                true
            }
        }
    }

    override fun getItemCount(): Int = tasks?.size ?: 0
    fun updateTasks(tasksList: List<Task>) {
        this.tasks = tasksList
        notifyDataSetChanged()
    }

    class ViewHolder(var itemBinding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(task: Task) {
            itemBinding.titleCard.text = task.nameTask
            itemBinding.descCard.text = task.descriptionTask
        }
    }
}