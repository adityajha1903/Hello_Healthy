package com.example.adi.hellohealthy.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "miscellaneousTable")
data class MiscellaneousExercisesEntity(
    @PrimaryKey
    val id: Int
)