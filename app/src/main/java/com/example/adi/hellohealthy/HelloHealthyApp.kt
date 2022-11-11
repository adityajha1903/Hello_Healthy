package com.example.adi.hellohealthy

import android.app.Application

class HelloHealthyApp: Application() {

    val historyDb by lazy {
        HistoryDataBase.getInstance(this)
    }

    val miscellaneousExercisesDb by lazy {
        MiscellaneousExercisesDataBase.getInstance(this)
    }

    val chestExerciseDb by lazy {
        ChestExerciseDataBase.getInstance(this)
    }

    val backExerciseDb by lazy {
        BackExerciseDataBase.getInstance(this)
    }

    val bicepExerciseDb by lazy {
        BicepExerciseDataBase.getInstance(this)
    }

    val tricepAndAbsDb by lazy {
        TricepAndAbsDataBase.getInstance(this)
    }

    val shoulderDb by lazy {
        ShoulderDataBase.getInstance(this)
    }

    val legsDb by lazy {
        LegsDataBase.getInstance(this)
    }
}