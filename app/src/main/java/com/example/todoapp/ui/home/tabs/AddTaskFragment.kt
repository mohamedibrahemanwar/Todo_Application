package com.example.todo.ui.home.tabs

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import com.example.todo.databases.MyDataBase
import com.example.todo.databases.Task
import com.example.todoapp.databinding.FragmentAddBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class AddTaskFragment : BottomSheetDialogFragment() {
 lateinit var viewBinding: FragmentAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentAddBinding.inflate(inflater,container,false)
        return viewBinding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.addTaskBtn.setOnClickListener {
            createTask()
        }
        viewBinding.dateContainer.setOnClickListener {
            showDatePickerDialog()
        }
    }
val calender = Calendar.getInstance()
    private fun showDatePickerDialog() {
        context?.let {
            val dialog = DatePickerDialog(it)
            dialog.setOnDateSetListener { datePicket, year, month, day ->
                viewBinding.date.text = "$day-${month+1}-$year"
                calender.set(year,month,day)
                // to ignore time
                calender.set(Calendar.HOUR_OF_DAY,0)
                calender.set(Calendar.MINUTE,0)
                calender.set(Calendar.SECOND,0)
                calender.set(Calendar.MILLISECOND,0)
            }
            dialog.show()
        }

    }

    private fun valid(): Boolean {
        var isValied =  true
    if (viewBinding.title.text.toString().isNullOrBlank()){
        viewBinding.titleContainer.error = "Please Enter title"
        isValied =  false
    } else{
        viewBinding.titleContainer.error = null
    }
    if(viewBinding.desc.text.toString().isNullOrBlank()){
        viewBinding.descContainer.error = "Please Enter Descrptaion"
        isValied =  false
    } else {
        viewBinding.descContainer.error = null
    }
    if(viewBinding.date.text.toString().isNullOrBlank()){
        viewBinding.dateContainer.error = "Please Choose date"
        isValied =  false
    } else {
        viewBinding.dateContainer.error = null
    }
        return  isValied
    }

    private fun createTask() {
         if(!valid()){
             return
         }
        val task = Task(
            nameTask = viewBinding.title.text.toString(),
            descriptionTask = viewBinding.desc.text.toString(),
            dateTime = calender.timeInMillis
        )
        MyDataBase.getInstance(requireContext())
            .TasksDao()
            .insertTask(task)
        onTaskAddedListener?.onTaskAdded()
            dismiss()
    }
    var onTaskAddedListener : OnTaskAddedListener? = null
    fun interface OnTaskAddedListener{
        fun onTaskAdded()
    }
}