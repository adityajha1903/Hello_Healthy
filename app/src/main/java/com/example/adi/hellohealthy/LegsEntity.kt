package com.example.adi.hellohealthy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "legsTable")
data class LegsEntity(
    @PrimaryKey
    val id: Int
)