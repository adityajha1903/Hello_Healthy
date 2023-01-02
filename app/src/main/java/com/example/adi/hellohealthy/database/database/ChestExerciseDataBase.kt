package com.example.adi.hellohealthy.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.adi.hellohealthy.database.entity.ChestExerciseEntity
import com.example.adi.hellohealthy.database.dao.ChestExerciseDao

@Database(entities = [ChestExerciseEntity::class], version = 1)
abstract class ChestExerciseDataBase: RoomDatabase() {

    abstract fun chestExerciseDao(): ChestExerciseDao

    companion object {
        @Volatile
        private var INSTANCE: ChestExerciseDataBase? = null
        private const val CHEST_EXERCISE_DB_NAME = "chest_database"

        fun getInstance(context: Context): ChestExerciseDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, ChestExerciseDataBase::class.java, CHEST_EXERCISE_DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }

}