package com.example.todo.ui.home.tabs.tasks_list

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.databases.MyDataBase
import com.example.todo.databases.Task
import com.example.todoapp.databinding.FragmentListBinding
import com.example.todo.ui.home.EditTaskActivity

class ListFragment : Fragment() {
    lateinit var viewBinding : FragmentListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentListBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onStart() {
        super.onStart()
        loadDate()
    }

    public fun loadDate() {
        context?.let {
            val tasksList =MyDataBase.getInstance(requireContext())
            .TasksDao()
            .allTasks()
            adapter.updateTasks(tasksList)}

    }

    var adapter = TasksAdapter(null)
    private fun initViews() {
        viewBinding.recyclerView.adapter =adapter
    }
}