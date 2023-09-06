package com.example.todoapp.ui.home.dateformat

import java.text.SimpleDateFormat
import java.util.Date

class Convertor {
    fun format(epoicTime: Long) : String{
        var date = Date(epoicTime)
        var format = SimpleDateFormat("dd-MM-yyyy")
        return format.format(date)
    }
    fun deformat(date:String):Long{
        val epoicTime: Date? = SimpleDateFormat("dd-MM-yyyy").parse(date)
        return epoicTime?.time ?: 0
    }
}