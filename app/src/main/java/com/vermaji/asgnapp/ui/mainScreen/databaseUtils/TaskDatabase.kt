package com.vermaji.asgnapp.ui.mainScreen.databaseUtils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [TaskModel::class], version = 1)
abstract class TaskDatabase : RoomDatabase(){
    abstract val taskDatabaseDao:TaskDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE:TaskDatabase?=null
        fun getInstance(context: Context):TaskDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java,
                        "taskdatabase"
                    ).fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}