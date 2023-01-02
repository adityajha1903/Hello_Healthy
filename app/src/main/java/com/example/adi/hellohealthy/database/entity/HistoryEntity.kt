package com.example.adi.hellohealthy.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history-table")
data class HistoryEntity(
    @PrimaryKey
    val date: String = "",

    @ColumnInfo(name = "set_name")
    val name: String = "",

    @ColumnInfo(name = "exercise_list")
    val exerciseListString: String = ""
)