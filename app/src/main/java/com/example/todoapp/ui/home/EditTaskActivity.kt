package com.example.todo.ui.home

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.example.todo.databases.MyDataBase
import com.example.todo.databases.Task

import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityEditTaskBinding
import com.example.todoapp.ui.home.dateformat.Convertor

class EditTaskActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityEditTaskBinding
    private lateinit var newtask: Task
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityEditTaskBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        newtask = ((intent.getSerializableExtra("OBJ_KEY") as Task?)!!)

        viewBinding.saveEdit.setOnClickListener {
            updateToDo()
        }
        viewBinding.date.setOnClickListener {
            Toast.makeText(this,"Clicked",Toast.LENGTH_LONG).show()
            showDatePickerDialog()
        }
    }

    private fun valid(): Boolean {
        var isValied = true
        if (viewBinding.title.text.toString().isNullOrBlank()) {
            viewBinding.containerTitle.error = "Please Enter title"
            isValied = false
        } else {
            viewBinding.containerTitle.error = null
        }
        if (viewBinding.desc.text.toString().isNullOrBlank()) {
            viewBinding.containerDesc.error = "Please Enter Descrptaion"
            isValied = false
        } else {
            viewBinding.containerDesc.error = null
        }
        if(viewBinding.date.text.toString().isNullOrBlank()){
            viewBinding.containerDate.error = "Please Choose date"
            isValied =  false
        } else {
            viewBinding.containerDate.error = null
        }
        return isValied
    }

    private fun updateToDo() {
        if (!valid()) {
            return
        }

        newtask.nameTask = viewBinding.title.text.toString()
        newtask.descriptionTask = viewBinding.desc.text.toString()
        var epoicTime = Convertor()
        newtask.dateTime = epoicTime.deformat(viewBinding.date.text.toString())
        MyDataBase.getInstance(this).TasksDao().updateTask(newtask)
        finish()

    }
    val calendar = Calendar.getInstance()
    private fun showDatePickerDialog() {
            val dialog = DatePickerDialog(this)
            dialog.setOnDateSetListener { datePicket, year, month, day ->
                viewBinding.date.text = "$day-${month+1}-$year" // set 3shan tzhr f el text
                calendar.set(year,month,day) // b set el balue
                // to ignore time
                calendar.set(java.util.Calendar.HOUR_OF_DAY,0)
                calendar.set(java.util.Calendar.MINUTE,0)
                calendar.set(java.util.Calendar.SECOND,0)
                calendar.set(java.util.Calendar.MILLISECOND,0)
            }
            dialog.show()

    }

}