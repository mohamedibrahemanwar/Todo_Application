package com.example.todo.ui.home

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.example.todo.databases.MyDataBase
import com.example.todo.databases.Task

import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityEditTaskBinding

class EditTaskActivity : AppCompatActivity() {
    lateinit var viewBinding : ActivityEditTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityEditTaskBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initParms()
        bindData()
        viewBinding.saveEdit.setOnClickListener {
            updateToDo()
        }
    }
    private fun valid(): Boolean {
        var isValied =  true
        if (viewBinding.title.text.toString().isNullOrBlank()){
            viewBinding.containerTitle.error = "Please Enter title"
            isValied =  false
        } else{
            viewBinding.containerTitle.error = null
        }
        if(viewBinding.desc.text.toString().isNullOrBlank()){
            viewBinding.containerDesc.error = "Please Enter Descrptaion"
            isValied =  false
        } else {
            viewBinding.containerDesc.error = null
        }
        if(viewBinding.date.text.toString().isNullOrBlank()){
            viewBinding.containerDate.error = "Please Choose date"
            isValied =  false
        } else {
            viewBinding.containerDate.error = null
        }
        return  isValied
    }
    private fun updateToDo() {
        if(!valid()){
            return }

        newtask?.nameTask = viewBinding.title.text.toString()
        newtask?.descriptionTask = viewBinding.desc.text.toString()
        MyDataBase.getInstance(this).TasksDao().updateTask(newtask!!)
        finish()

    }

    private fun bindData() {
        viewBinding.title.text = newtask?.nameTask?.let {
            Editable
                .Factory
                .getInstance()
                .newEditable(it)
        }
        viewBinding.desc.text = newtask?.descriptionTask?.let {
            Editable
                .Factory
                .getInstance()
                .newEditable(it)
        }
    }


     var newtask : Task? = null
    private fun initParms() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            newtask = intent.getSerializableExtra("OBJ_KEY",Task::class.java)!!
        } else {
            newtask = intent.getSerializableExtra("OBJ_KEY") as Task
        }
    }
}