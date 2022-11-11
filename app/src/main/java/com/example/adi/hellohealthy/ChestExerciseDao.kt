package com.example.adi.hellohealthy

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChestExerciseDao {

    @Query("select * from `chestTable`")
    suspend fun fetchAllChestExercises(): List<Int>

    @Insert
    suspend fun insert(chestExercisesEntity: ChestExerciseEntity)

    @Delete
    suspend fun delete(chestExercisesEntity: ChestExerciseEntity)

}