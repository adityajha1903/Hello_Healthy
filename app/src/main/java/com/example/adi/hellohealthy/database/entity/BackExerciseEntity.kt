package com.example.adi.hellohealthy.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "backTable")
data class BackExerciseEntity(
    @PrimaryKey
    val id: Int
)