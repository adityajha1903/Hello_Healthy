package com.example.adi.hellohealthy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chestTable")
data class ChestExerciseEntity (
    @PrimaryKey
    val id: Int
)