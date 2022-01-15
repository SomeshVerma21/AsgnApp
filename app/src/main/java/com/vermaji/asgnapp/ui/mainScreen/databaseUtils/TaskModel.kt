package com.vermaji.asgnapp.ui.mainScreen.databaseUtils

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taskdatabase")
data class TaskModel(
    @PrimaryKey(autoGenerate = true) val id:Int?,
    @ColumnInfo(name = "tasktitle") val title:String,
    @ColumnInfo(name = "taskdesc") val description:String,
    @ColumnInfo(name = "taskdate") val date:String
)
