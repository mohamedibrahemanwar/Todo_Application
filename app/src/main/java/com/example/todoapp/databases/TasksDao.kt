package com.example.todo.databases

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.selects.select

@Dao
interface TasksDao {
    @Insert
fun insertTask(task: Task)
@Delete
fun deletTask(task: Task)
@Update
fun updateTask(task: Task)
@Query("select * from (tasks)")
fun allTasks() : List<Task>
@Query("select * from tasks where dateTime = :dateTime ")
fun allTasksbyDate(dateTime:Long) : List<Task>
}