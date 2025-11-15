package com.example.beer.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.beer.data.dao.BeerDao
import com.example.beer.data.dao.RatingDao
import com.example.beer.data.dao.TasteDao
import com.example.beer.data.model.BeerModel
import com.example.beer.data.model.RatingModel
import com.example.beer.data.model.TasteModel

@Database(
    entities = [BeerModel::class, RatingModel::class, TasteModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun beerDao(): BeerDao
    abstract fun ratingDao(): RatingDao
    abstract fun tasteDao(): TasteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                try {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app.db"
                    )
                        .createFromAsset("seed.db")
                        .fallbackToDestructiveMigration(false)
                        //.allowMainThreadQueries() //TODO: Wieder entfernen
                        .build()

                    INSTANCE = instance
                    instance
                } catch (e: Exception) {
                    Log.e("AppDatabase", "Fehler beim Initialisieren der Datenbank", e)

                    // Fallback auf leere In-Memory-DB, damit App weiterläuft
                    Room.inMemoryDatabaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java
                    )
                        .allowMainThreadQueries()
                        .build()
                        .also {
                            Log.w("AppDatabase", "Fallback auf In-Memory-DB aktiviert!")
                            INSTANCE = it
                        }
                }
            }
        }
    }
}