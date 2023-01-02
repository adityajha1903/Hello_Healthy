package com.example.adi.hellohealthy.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.adi.hellohealthy.database.entity.BackExerciseEntity
import com.example.adi.hellohealthy.database.dao.BackExerciseDao

@Database(entities = [BackExerciseEntity::class], version = 1)
abstract class BackExerciseDataBase: RoomDatabase() {

    abstract fun backExerciseDao(): BackExerciseDao

    companion object {
        @Volatile
        private var INSTANCE: BackExerciseDataBase? = null
        private const val BACK_EXERCISE_DB_NAME = "back_database"

        fun getInstance(context: Context): BackExerciseDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, BackExerciseDataBase::class.java, BACK_EXERCISE_DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}