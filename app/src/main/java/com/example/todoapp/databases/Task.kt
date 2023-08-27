package com.example.todo.databases

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var idTask : Int? = null,
    var nameTask : String? = null,
    var descriptionTask:String? = null,
    var dateTime : Long? = null,
    var isDone :Boolean = false
) : Serializable
