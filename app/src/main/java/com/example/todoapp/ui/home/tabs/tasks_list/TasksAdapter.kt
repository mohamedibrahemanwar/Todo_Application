package com.example.todo.ui.home.tabs.tasks_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.todo.databases.Task
import com.example.todoapp.databinding.ItemRecyclerBinding
import com.zerobranch.layout.SwipeLayout.SwipeActionsListener

class TasksAdapter(var tasks: List<Task>?) : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks!![position])

        //call back for edit
        if (onItemClickListnerCard != null) {
            holder.itemBinding.card.setOnLongClickListener {
                onItemClickListnerCard?.onClick(position,tasks!![position])
                true
            }
        }

        // call back for delete
        holder.itemBinding.delete.setOnClickListener {
            onItemDeleteClickListnereCard?.onItemDeleteClick(position, tasks!![position])
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
//Call backs
    fun interface OnItemClickListnerCard {
        fun onClick(position: Int,task: Task)
    }

    var onItemDeleteClickListnereCard: OnItemDeleteClickListnereCard? = null

    fun interface OnItemDeleteClickListnereCard {
        fun onItemDeleteClick(position: Int, task: Task)
    }

    var onItemClickListnerCard: OnItemClickListnerCard? = null
}