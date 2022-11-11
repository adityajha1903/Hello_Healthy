package com.example.adi.hellohealthy

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MiscellaneousExercisesEntity::class], version = 1)
abstract class MiscellaneousExercisesDataBase: RoomDatabase() {

    abstract fun miscellaneousExercisesDao(): MiscellaneousExercisesDao

    companion object {
        @Volatile
        private var INSTANCE: MiscellaneousExercisesDataBase? = null
        private const val MISCELLANEOUS_EXERCISE_DB_NAME = "miscellaneous_database"

        fun getInstance(context: Context): MiscellaneousExercisesDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, MiscellaneousExercisesDataBase::class.java, MISCELLANEOUS_EXERCISE_DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}