package com.example.adi.hellohealthy.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.adi.hellohealthy.database.entity.LegsEntity

@Dao
interface LegsDao {

    @Query("select * from `legsTable`")
    suspend fun fetchAllLegsExercises(): List<Int>

    @Insert
    suspend fun insert(legsEntity: LegsEntity)

    @Delete
    suspend fun delete(legsEntity: LegsEntity)
}