package com.example.adi.hellohealthy.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bicepTable")
data class BicepExerciseEntity(
    @PrimaryKey
    val id: Int
)