package com.example.adi.hellohealthy.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.adi.hellohealthy.database.dao.LegsDao
import com.example.adi.hellohealthy.database.entity.LegsEntity

@Database(entities = [LegsEntity::class], version = 1)
abstract class LegsDataBase: RoomDatabase() {

    abstract fun legsDao(): LegsDao

    companion object {
        @Volatile
        private var INSTANCE: LegsDataBase? = null
        private const val LEGS_EXERCISE_DB_NAME = "legs_database"

        fun getInstance(context: Context): LegsDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, LegsDataBase::class.java, LEGS_EXERCISE_DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}