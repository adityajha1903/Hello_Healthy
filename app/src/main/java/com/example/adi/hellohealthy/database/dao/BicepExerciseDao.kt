package com.example.adi.hellohealthy.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.adi.hellohealthy.database.entity.BicepExerciseEntity

@Dao
interface BicepExerciseDao {

    @Query("select * from `bicepTable`")
    suspend fun fetchAllBicepExercises(): List<Int>

    @Insert
    suspend fun insert(bicepExercisesEntity: BicepExerciseEntity)

    @Delete
    suspend fun delete(bicepExercisesEntity: BicepExerciseEntity)
}