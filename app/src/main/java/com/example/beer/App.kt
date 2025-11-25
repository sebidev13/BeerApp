package com.example.beer

import android.app.Application
import com.example.beer.data.AppDatabase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application(){

    @Inject
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        // Zugriff erzwingt, dass Hilt DB baut
        database.openHelper.writableDatabase  // legt app.db an
    }
}