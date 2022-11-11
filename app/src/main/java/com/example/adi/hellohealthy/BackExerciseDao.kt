package com.example.adi.hellohealthy

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BackExerciseDao {

    @Query("select * from `backTable`")
    suspend fun fetchAllBackExercises(): List<Int>

    @Insert
    suspend fun insert(backExercisesEntity: BackExerciseEntity)

    @Delete
    suspend fun delete(backExercisesEntity: BackExerciseEntity)
}