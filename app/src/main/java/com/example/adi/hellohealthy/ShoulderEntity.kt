package com.example.adi.hellohealthy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shoulderTable")
data class ShoulderEntity(
    @PrimaryKey
    val id: Int
)