package com.example.adi.hellohealthy

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MiscellaneousExercisesDao {

    @Query("select * from `miscellaneousTable`")
    suspend fun fetchAllMiscellaneousExercises(): List<Int>

    @Insert
    suspend fun insert(miscellaneousExercisesEntity: MiscellaneousExercisesEntity)

    @Delete
    suspend fun delete(miscellaneousExercisesEntity: MiscellaneousExercisesEntity)
}