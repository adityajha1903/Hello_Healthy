package com.example.adi.hellohealthy.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.adi.hellohealthy.database.entity.MiscellaneousExercisesEntity

@Dao
interface MiscellaneousExercisesDao {

    @Query("select * from `miscellaneousTable`")
    suspend fun fetchAllMiscellaneousExercises(): List<Int>

    @Insert
    suspend fun insert(miscellaneousExercisesEntity: MiscellaneousExercisesEntity)

    @Delete
    suspend fun delete(miscellaneousExercisesEntity: MiscellaneousExercisesEntity)
}