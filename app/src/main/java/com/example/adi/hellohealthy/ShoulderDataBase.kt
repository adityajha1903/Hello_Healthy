package com.example.adi.hellohealthy

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShoulderEntity::class], version = 1)
abstract class ShoulderDataBase: RoomDatabase() {

    abstract fun shoulderDao(): ShoulderDao

    companion object {
        @Volatile
        private var INSTANCE: ShoulderDataBase? = null
        private const val SHOULDER_EXERCISE_DB_NAME = "shoulder_database"

        fun getInstance(context: Context): ShoulderDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, ShoulderDataBase::class.java, SHOULDER_EXERCISE_DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}