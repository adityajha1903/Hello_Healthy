package com.example.adi.hellohealthy

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TricepAndAbsEntity::class], version = 1)
abstract class TricepAndAbsDataBase: RoomDatabase() {

    abstract fun tricepAndAbsDao(): TricepAndAbsDao

    companion object {
        @Volatile
        private var INSTANCE: TricepAndAbsDataBase? = null
        private const val TRICEP_AND_ABS_EXERCISE_DB_NAME = "tricep_and_abs_database"

        fun getInstance(context: Context): TricepAndAbsDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, TricepAndAbsDataBase::class.java, TRICEP_AND_ABS_EXERCISE_DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}