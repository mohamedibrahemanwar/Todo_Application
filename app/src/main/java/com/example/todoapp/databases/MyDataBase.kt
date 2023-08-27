package com.example.todo.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1, exportSchema = true)
abstract class MyDataBase: RoomDatabase() {
    abstract fun TasksDao():TasksDao
companion object{
    private var instance : MyDataBase? = null

    fun getInstance(context : Context): MyDataBase {
        if (instance == null) {
            //inizalize
            instance = Room.databaseBuilder(context.applicationContext
                , MyDataBase::class.java, "TASKSDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
        return instance!!
    }
    }
}