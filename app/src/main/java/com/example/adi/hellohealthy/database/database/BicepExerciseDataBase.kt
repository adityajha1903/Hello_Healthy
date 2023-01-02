package com.example.adi.hellohealthy.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.adi.hellohealthy.database.entity.BicepExerciseEntity
import com.example.adi.hellohealthy.database.dao.BicepExerciseDao

@Database(entities = [BicepExerciseEntity::class], version = 1)
abstract class BicepExerciseDataBase: RoomDatabase() {

    abstract fun bicepExerciseDao(): BicepExerciseDao

    companion object {
        @Volatile
        private var INSTANCE: BicepExerciseDataBase? = null
        private const val BICEP_EXERCISE_DB_NAME = "bicep_database"

        fun getInstance(context: Context): BicepExerciseDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, BicepExerciseDataBase::class.java, BICEP_EXERCISE_DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}