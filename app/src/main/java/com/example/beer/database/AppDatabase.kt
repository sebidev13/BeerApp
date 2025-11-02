package com.example.beer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.beer.database.dao.BeerDao
import com.example.beer.database.dao.RatingDao
import com.example.beer.database.dao.TasteDao
import com.example.beer.database.entities.Beer
import com.example.beer.database.entities.Rating
import com.example.beer.database.entities.Taste

@Database(
    entities = [Beer::class, Rating::class, Taste::class],
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
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "beer_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}