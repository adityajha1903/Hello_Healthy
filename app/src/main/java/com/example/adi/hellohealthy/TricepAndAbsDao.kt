package com.example.adi.hellohealthy

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TricepAndAbsDao {

    @Query("select * from `tricepAndAbsTable`")
    suspend fun fetchAllTricepAndAbsExercises(): List<Int>

    @Insert
    suspend fun insert(tricepAndAbsEntity: TricepAndAbsEntity)

    @Delete
    suspend fun delete(tricepAndAbsEntity: TricepAndAbsEntity)
}