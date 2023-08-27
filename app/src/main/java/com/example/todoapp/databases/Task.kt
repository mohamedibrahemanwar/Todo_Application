package com.example.todo.databases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var idTask : Int? = null,
    var nameTask : String? = null,
    var descriptionTask:String? = null,
    var dateTime : Long? = null,
    var isDone :Boolean = false
)
