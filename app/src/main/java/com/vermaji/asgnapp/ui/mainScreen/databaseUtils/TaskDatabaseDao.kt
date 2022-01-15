package com.vermaji.asgnapp.ui.mainScreen.databaseUtils

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface TaskDatabaseDao {
    @Insert
    fun insertTask(task:TaskModel)

    @Query("Select * from taskdatabase")
    fun getAll():List<TaskModel>
}