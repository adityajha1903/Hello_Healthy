package com.example.adi.hellohealthy.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tricepAndAbsTable")
data class TricepAndAbsEntity(
    @PrimaryKey
    val id: Int
)