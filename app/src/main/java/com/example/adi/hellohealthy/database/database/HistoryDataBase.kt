package com.example.adi.hellohealthy.database.database

import android.content.Context
import androidx.room.*
import com.example.adi.hellohealthy.database.dao.HistoryDao
import com.example.adi.hellohealthy.database.entity.HistoryEntity

@Database(entities = [HistoryEntity::class], version = 1)
abstract class HistoryDataBase: RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    companion object {
        @Volatile
        private var INSTANCE: HistoryDataBase? = null
        private const val HISTORY_DB_NAME = "history_database"

        fun getInstance(context: Context): HistoryDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, HistoryDataBase::class.java, HISTORY_DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }


}