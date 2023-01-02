package com.example.adi.hellohealthy.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.adi.hellohealthy.database.entity.ChestExerciseEntity

@Dao
interface ChestExerciseDao {

    @Query("select * from `chestTable`")
    suspend fun fetchAllChestExercises(): List<Int>

    @Insert
    suspend fun insert(chestExercisesEntity: ChestExerciseEntity)

    @Delete
    suspend fun delete(chestExercisesEntity: ChestExerciseEntity)

}