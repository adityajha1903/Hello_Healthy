package com.example.adi.hellohealthy.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.adi.hellohealthy.database.entity.ShoulderEntity

@Dao
interface ShoulderDao {

    @Query("select * from `shoulderTable`")
    suspend fun fetchAllShoulderExercises(): List<Int>

    @Insert
    suspend fun insert(shoulderEntity: ShoulderEntity)

    @Delete
    suspend fun delete(shoulderEntity: ShoulderEntity)
}